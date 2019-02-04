#include <stdio.h>
#include <limits.h>

int main(){
  printf("NAME_MAX : longueur maximale d'un nom d'entrée dans le système de fichiers = %i\nPATH_MAX : longueur maximale d'un chemin dans le système de fichiers = %i\n", (int)NAME_MAX, (int)PATH_MAX);
  return 0;
}
