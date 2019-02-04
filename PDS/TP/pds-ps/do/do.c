#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <sys/wait.h>
#include <unistd.h>

#include "makeargv.h"

int aflag, oflag, cflag, kflag;

#define EVAL_STATUS(status) (WIFEXITED(status) && ! WEXITSTATUS(status))

int mdo(int argc, char *argv[]);
void usage(char *prog);

void usage(char *prog){
  printf("usage : %s do [aock]\n", prog);
}

int mdo(int argc, char *argv[]){
  int status, res, i, y;
  pid_t pid, *tmp;
  tmp = (pid_t *) malloc(sizeof(pid_t)*argc);
  assert(tmp!=NULL);

  for(i = 0; i < argc; i++){
    char **cmd;
    cmd = makeargv(argv[i]);
    assert(cmd!=NULL);
    switch(pid=fork()){
      case -1:
        exit(EXIT_FAILURE);
      case 0:
        execvp(cmd[0],cmd);
        perror("une des commandes n'a pas pu être lancé !");
        exit(EXIT_FAILURE);
      default:
        tmp[i] = pid;
        freeargv(cmd);
    }
  }

  for(i=0; i < argc; i++){
    wait(&status);
    if(aflag){
      res &= EVAL_STATUS(status);
      if(cflag && !res){
        if(kflag){
          for(y = 0; y < argc; y++){
            kill(tmp[y], SIGKILL);
          }
        free(tmp);
        return EXIT_FAILURE;
        }
      }
    }else{
      res |= EVAL_STATUS(status);
      if(cflag && res){
        if(kflag){
          for(y = 0; y < argc; y++){
            kill(tmp[y], SIGKILL);
          }
        }
        free(tmp);
        return EXIT_SUCCESS;
      }
    }
  }

  free(tmp);
  if(res){
    return EXIT_SUCCESS;
  }else{
    return EXIT_FAILURE;
  }
}

int main(int argc, char *argv[]){
  char param;
  aflag = 1;
  cflag = 0;
  oflag = 0;
  kflag = 0;
  while((param = getopt(argc, argv, "aock"))!=-1){
    switch(param){
      case 'a':
        aflag = 1;
        break;
      case 'o':
        cflag = 1;
        break;
      case 'c':
        cflag = 1;
        break;
      case 'k':
        kflag = 1;
        break;
      default:
        usage(argv[0]);
    }
  }
  argc -= optind;
  argv += optind;
  exit(mdo(argc, argv));
}
