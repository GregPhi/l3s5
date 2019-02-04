#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <fcntl.h>


/* --- PROTOTYPE FUNCTION --- */
int isFile(const char *pathname);
int mcat_scd(const char *pathname);
/* --- FUNCTION --- */

/*
 * vérifier qu'il s'agit bien d'un fichier
 * --> inutile ici car utuilisation de file_descriptor == -1
*/
/*int isFile(const char *pathname){
    struct stat s;
    int test_s;

    test_s = stat(pathname, &s);
    assert(test_s!=-1);

    if(S_ISREG(s.st_mode)){
      return EXIT_SUCCESS;
    }
    return EXIT_FAILURE;
}*/

/* lecture du fichier de manière non dynamique
int mcat_scd(const char *pathname){
  int file_descriptor;
  char buffer[1024];
  int size;

  if(isFile(pathname)){
    return EXIT_FAILURE;
  }

  file_descriptor = open(pathname, O_RDONLY);
  if(file_descriptor == -1){
    printf("file\n");
    return EXIT_FAILURE;
  }

  size = read(file_descriptor, buffer, 1024);
  buffer[size] = 0;
  write(STDOUT_FILENO, buffer, size);
  close(file_descriptor);

  return EXIT_SUCCESS;
}*/

/* lecture du fichier */
int mcat_scd(const char *pathname){
  int file_descriptor;
  char *buffer;
  int size;
  char *tampon;
  int i = 0;

   /*if(isFile(pathname)){
     return EXIT_FAILURE;
   }*/

  buffer = getenv("MCAT_BUFSIZE");
  size = atoi(buffer);
  tampon = malloc(sizeof(char)*size);

  file_descriptor = open(pathname, O_RDONLY);
  if(file_descriptor == -1){
    printf("file does'nt exist\n");
    return EXIT_FAILURE;
  }


  while(read(file_descriptor, tampon+i, 1)){
    while(i < size){
      i++;
    }
    write(STDOUT_FILENO, tampon, size);
    i=0;
  }

  close(file_descriptor);

  return EXIT_SUCCESS;
}

/** MAIN
 * test argv is a file
 * test read argv
 */
int main(int argc, char *argv[]){
  assert(argc>=2);
  mcat_scd(argv[1]);
  printf("\n");
  return EXIT_SUCCESS;
}
