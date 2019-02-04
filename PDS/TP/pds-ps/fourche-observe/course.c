#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <stdio.h>

int main(){
  int i, j, status;
  /* 10 : participants de la course */
  pid_t participant[10];

  printf("COURSE :\n");
  fflush(stdout);

  for(i = 0; i < 10; i++){
    switch (fork()) {
      case -1:
        return EXIT_FAILURE;
      case 0:
        printf("%d prend le départ\n", i);
        for(j = 0; j < 100000000; j++);
        printf("le participant %d a passé l'étape 1\n",i);
        fflush(stdout);
        for(j = 0; j < 100000000; j++);
        exit(i);
    }
  }

  /* est ce que l'exécution des fils est finies ?*/
  for(i = 0; i < 10; i++){
    wait(&status);
    if(WIFEXITED(status)){
      participant[i] = WEXITSTATUS(status) + 1;
    }else{
      return EXIT_FAILURE;
    }
  }

  printf("\n\nAffichage arrivée : \n");
  for(i = 0; i < 10; i++){
    printf("Le participant %d est arrivé à la %d place \n", participant[i],i+1);
  }

  return EXIT_SUCCESS;
}
