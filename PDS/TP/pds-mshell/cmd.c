/* mshell - a job manager */

#include <stdio.h>
#include <signal.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include "jobs.h"
#include "cmd.h"

#define NC  "\x1B[0m"
#define RED "\x1B[31m"

const char* jstate_to_string[] = {"UNDEF", "BG", "FG", "ST"};

void do_help() {
    printf("available commands are:\n");
    printf(" exit - cause the shell to exit\n");
    printf(BOLD "\t exit\n" NORM);
    printf(" jobs - display status of jobs in the current session\n");
    printf(BOLD "\t jobs\n" NORM);
    printf(" fg   - run a job identified by its pid or job id in the foreground\n");
    printf(BOLD "\t fg " NORM "pid" BOLD "|" NORM "jobid \n");
    printf(" bg   - run a job identified by its pid or job id in the background\n");
    printf(BOLD "\t bg " NORM "pid" BOLD "|" NORM "jobid \n");
    printf(" stop - stop a job identified by its pid or job id\n");
    printf(BOLD "\t stop " NORM "pid" BOLD "|" NORM "jobid \n");
    printf(" kill - kill a job identified by its pid or job id\n");
    printf(BOLD "\t kill " NORM "pid" BOLD "|" NORM "jobid \n");
    printf(" help - print this message\n");
    printf(BOLD "\t help\n" NORM);
    printf("\n");
    printf("ctrl-z and ctrl-c can be used to send a SIGTSTP and a SIGINT, respectively\n\n");
}

/* treat_argv - Determine pid or jobid and return the associated job structure */
struct job_t *treat_argv(char **argv) {
    struct job_t *jobp = NULL;

    /* Ignore command if no argument */
    if (argv[1] == NULL) {
        printf("%s command requires PID or %%jobid argument\n", argv[0]);
        return NULL;
    }

    /* Parse the required PID or %JID arg */
    if (isdigit((int) argv[1][0])) {
        pid_t pid = atoi(argv[1]);
        if (!(jobp = jobs_getjobpid(pid))) {
            printf("(%d): No such process\n", (int) pid);
            return NULL;
        }
    } else if (argv[1][0] == '%') {
        int jid = atoi(&argv[1][1]);
        if (!(jobp = jobs_getjobjid(jid))) {
            printf("%s: No such job\n", argv[1]);
            return NULL;
        }
    } else {
        printf("%s: argument must be a PID or %%jobid\n", argv[0]);
        return NULL;
    }

    return jobp;
}

/* waitfg - Block until process pid is no longer the foreground process */
void waitfg(pid_t pid) {
  struct job_t *job;
  sigset_t mask;

  if (verbose) {
    printf("%sWaitfg : entering%s\n", RED, NC);
  }

  job = jobs_getjobpid(pid);
  sigprocmask(SIG_UNBLOCK, &mask, NULL);

  while ((job->jb_state) == FG) {
    printf("Still waiting for %d with state %s\n", job->jb_pid, jstate_to_string[job->jb_state]);
    sleep(1);
  }

  if (verbose) {
    printf("%sWaitfg : exiting%s\n", RED, NC);
  }

  return;
}

/* do_fg - Execute the builtin fg command, resume a stopped process and
place as main process */
void do_fg(char **argv) {
  struct job_t *job = treat_argv(argv);

  if (job != NULL) {
    pid_t pid = job->jb_pid;

    if (verbose) {
      printf("Do_fg : entering\n");
    }

    if (pid != 0 && jobs_fgpid() == 0) {
      job->jb_state = FG;

      if (kill(-pid, SIGCONT) < 0) {
        unix_error("Sigcont resumer: Failed\n");
      } else {
        printf("Job %d resumed\n", pid);
      }

      waitfg(pid);
    }

    if (verbose) {
      printf("Do_fg : exiting\n");
    }
  }

  return;
}

/* do_bg - Execute the builtin bg command, resume a stopped process and
keep it as BG process */
void do_bg(char **argv) {
  struct job_t *job = treat_argv(argv);

  if (job != NULL) {
    pid_t pid = job->jb_pid;

    if (verbose) {
      printf("Do_bg : entering\n");
    }

    if (pid != 0) {
      job->jb_state = BG;

      if (kill(-pid, SIGCONT) < 0) {
        unix_error("Sigcont resumer: Failed\n");
      } else {
        printf("Job %d resumed\n", pid);
      }
    }

    if (verbose) {
      printf("Do_bg : exiting\n");
    }
  }

  return;
}

/* do_kill - Execute the builtin kill command */
void do_kill(char **argv) {
  struct job_t *job = treat_argv(argv);

  if (job != NULL) {
    pid_t pid = job->jb_pid;

    if (verbose) {
      printf("Do_kill : entering\n");
    }

    if (pid != 0) {
      kill(pid, SIGKILL);
    }

    if (verbose) {
      printf("Do_kill : exiting\n");
    }
  }

  return;
}

/* do_stop - Execute the builtin stop command */
void do_stop(char **argv) {
  struct job_t *job = treat_argv(argv);

  if (job != NULL) {
    pid_t pid = job->jb_pid;

    if (verbose) {
      printf("do_stop : entering\n");
    }

    if (pid!=0) {
      kill(pid, SIGTSTP);
    }

    if (verbose) {
      printf("do_stop : exiting\n");
    }
  }

  return;
}

void do_jobs() {
  struct job_t *jobs = jobs_getall();
  struct job_t job;
  int i = 0;

  printf("Job list:\n");
  for (i = 0; i < (int)sizeof(jobs); i++) {
    job = jobs[i];
    if (job.jb_pid != 0) {
      printf("The job %d is %s\n", job.jb_pid, jstate_to_string[job.jb_state]);
    }
  }

  return;
}

/* do_exit - Execute the builtin exit command */
void do_exit() {
  struct job_t *job;
  /* as long as all the existing jobs are not kill, we can not get out of the mshell */
  while((job = jobs_getstoppedjob()) != NULL){
    kill(-job->jb_pid, SIGKILL);
  }
  printf("exit of mshell\n");
  exit(EXIT_SUCCESS);
}
