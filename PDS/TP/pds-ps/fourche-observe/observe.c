#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <stdio.h>

int main(int argc, char *argv[]){
  int nObserv = 5, i;
  pid_t observ, pid;

  for(i = 0; i < argc; i++){
    if(strcmp(argv[i],"-n") == 0){
      nObserv = atoi(argv[i+1]);
      break;
    }
  }
  /*printf("%d\n", nObserv);*/

  for(i = 0; i < nObserv; i++){
    switch (pid = fork()) {
      case -1:
        return EXIT_FAILURE;
      case 0:
        while(1){
          printf("pid %d\n", getpid());
          sleep(5);
        }
    }
  }

  system("ps -a");

  for(i = 0; i < nObserv; i++){
    observ = wait(NULL);
    printf("Le fils numéro : %d est terminé.\n", (int) observ);
    fflush(stdout);
  }

  return EXIT_SUCCESS;
}
