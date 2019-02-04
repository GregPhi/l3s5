#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <assert.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <stdio.h>

typedef int (*func_t) (char *);

/* SQUELETTE */
int multif (func_t f[], char *args[], int n);
int testfunc(char *chaine);

/* FUNCTION */
int multif (func_t f[], char *args[], int n){
  int status, i, res;

  for(i = 0; i < n; i++){
    switch(fork()){
        case -1:
          return EXIT_FAILURE;
        case 0: /*FILS*/
          exit(f[i](args[i]));
        default:
          break;
    }
  }

  for(i = 0; i < n; i++){
    wait(&status);
    if(WIFEXITED(status)){
      if (WEXITSTATUS(status) == EXIT_FAILURE){
        res = 1;
      }
    }else{
      return EXIT_FAILURE;
    }
  }

  if(res == 1){
    printf("PAS REUSSI\n");
    return EXIT_FAILURE;
  }else{
    printf("REUSSI\n");
    return EXIT_SUCCESS;
  }
}

int testfunc(char *chaine){
  if(strcmp(chaine, "true") == 0){
    /*printf("comparaison true\n");*/
    return EXIT_SUCCESS;
  }
  if(strcmp(chaine, "false") == 0){
    /*printf("comparaison false\n");*/
    return EXIT_FAILURE;
  }
  if(strcmp(chaine, "sleep") == 0){
    /*printf("comparaison sleep\n");*/
    sleep(5);
    return EXIT_SUCCESS;
  }
  /*printf("comparaison impossible\n");*/
  exit(EXIT_FAILURE);
}

/* MAIN */
int main(int argc, char *argv[]){
  int nTab;
  func_t *functs;

  assert(argc>=2);

  functs = (func_t *) malloc(sizeof(func_t) * (argc-1));
  for(nTab = 0; nTab < argc-1; nTab++){
    functs[nTab] = &testfunc;
  }

  return multif(functs, argv+1, argc-1);
}
