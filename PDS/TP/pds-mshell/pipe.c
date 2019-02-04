/* mshell - a job manager */

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#include "pipe.h"
#include "common.h"
#include "jobs.h"
#include "cmd.h"

int flag_ISSTDIN;

void redirectionCMD(int fd[2], int out, char *cmd[MAXARGS]){
  assert((dup2(fd[1],1) != -1) && close(fd[0]) == 0);
  assert((dup2(out, 0) != -1) && execvp(cmd[0],cmd) == 0);
}

void closeCMD(int fd[2], int out){
  assert(close(fd[1]) == 0);
  if(flag_ISSTDIN == 1){
    assert(close(out) == 0);
  }else{
    flag_ISSTDIN = 1;
  }
}

void do_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg) {
  int fd[2];
  int out = 0;
  int i;
  pid_t pid;
  char *cmd[MAXARGS];

  flag_ISSTDIN = 0;

  for(i = -1; i <= nbcmd -2; i++){
    assert(pipe(fd) == 0);
    cmd[0] = cmds[i][0];
    switch(fork()){
      case 0:
        redirectionCMD(fd, out, cmd);
        break;
      case 1:
        unix_error("error fork in for do_pipe");
        exit(EXIT_FAILURE);
      default:
        wait(NULL);
        closeCMD(fd, out);
    }
    out = fd[0];
  }

  switch(pid = fork()){
    case 0:
      cmd[0] = cmds[nbcmd-1][0];
      assert(dup2(out,0) != -1);
      assert(execvp(cmd[0], cmd) != -1);
      break;
    case -1:
      unix_error("fork error");
      exit(EXIT_FAILURE);
    default:
      wait(NULL);
  }

  if(bg == 0){
    waitfg(pid);
  }

  return;
}
