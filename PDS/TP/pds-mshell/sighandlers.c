/* mshell - a job manager */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <signal.h>
#include <sys/wait.h>
#include <errno.h>
#include <assert.h>

#include "jobs.h"
#include "common.h"
#include "sighandlers.h"

/*
 * wrapper for the sigaction function
 */
int sigaction_wrapper(int signum, handler_t * handler) {
  struct sigaction sigAction;

  if (verbose) {
    printf("Sigaction wrapper: Entering for signum %d\n", signum);
  }

  /* Associat handler function on signal */
  sigAction.sa_handler = handler;
  sigemptyset(&(sigAction.sa_mask));
  sigAction.sa_flags = SA_RESTART;
  if (sigaction(signum, &sigAction, NULL) < 0) {
    unix_error("Sigaction wrapper: Fail");
  }

  if (verbose) {
    printf("Sigaction wrapper: Exiting, well done\n");
  }

  return 1;
}

/*
 * CTRL-C
 * sigint_handler - The kernel sends a SIGINT to the shell whenver the
 *    user types ctrl-c at the keyboard.  Catch it and send it along
 *    to the foreground job.
 */
void sigint_handler(int sig) {
  pid_t pid;

  /* Check if we are in the good sig */
  assert(sig == SIGINT);

  if (verbose){
    printf("Sigint handler: Entering, you have taping CTRL-C\n");
  }

  /* Get the pid of the fg process and if the pid is not 0, we kill it */
  pid = jobs_fgpid();
  if (pid > 0) {
    if (kill(-pid, SIGINT) < 0){
      unix_error("Sigint handler: Fail\n");
    } else {
      printf("Job %d killed\n", pid);
    }
  } else {
    if (verbose){
      printf("No current job is running\n");
    }
  }

  if (verbose){
    printf("Sigint handler: Exiting\n");
  }
  return;
}

/*
 * CTRL-Z
 * sigtstp_handler - The kernel sends a SIGTSTP to the shell whenever
 *     the user types ctrl-z at the keyboard. Catch it and suspend the
 *     foreground job by sending it a SIGTSTP.
 */
void sigtstp_handler(int sig) {
  pid_t pid;

  assert(sig == SIGTSTP);

  if (verbose) {
    printf("Sigtstp handler: Entering, you have taping CTRL-Z\n");
  }

  if ((pid = jobs_fgpid()) > 0) {
    if (kill(-pid, SIGTSTP) < 0) {
      unix_error("Sigtstp handler: Fail");
    }
   } else {
    if (verbose){
      printf("No current job is running\n");
    }
  }

  if (verbose) {
    printf("Sigtstp handler: Exiting\n");
  }
  return;
}

/*
 * sigchld_handler - The kernel sends a SIGCHLD to the shell whenever
 *     a child job terminates (becomes a zombie), or stops because it
 *     received a SIGSTOP or SIGTSTP signal. The handler reaps all
 *     available zombie children
 */
void sigchld_handler(int sig) {
  int status;
  pid_t pid;
  struct job_t *job;

  assert(sig == SIGCHLD);

  if (verbose) {
    printf("Sigchld handler: Entering\n");
  }

  while ((pid = waitpid(-1, &status, WNOHANG | WUNTRACED)) > 0) {

    if (verbose) {
      printf("Job found: %d\n", pid);
    }

    job = jobs_getjobpid(pid);
    if (WIFEXITED(status)) {
      jobs_deletejob(pid);
    }

    if (WIFSIGNALED(status)) {
      if (WTERMSIG(status) == SIGINT || WTERMSIG(status) == SIGKILL) {
          if (verbose) {
            printf("Job deleted: %d\n", pid);
          }
          jobs_deletejob(pid);
      }
    }

    if (WIFSTOPPED(status)) {
      if (job != NULL) {
        job->jb_state = ST;
      }
    }
  }

  if (verbose) {
    printf("Sigchld handler: Exiting\n");
  }
  return;
}
