#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <assert.h>
#include <stdio.h>
#include <dirent.h>
#include <string.h>
#include <limits.h>
#include <stdlib.h>

static int opt_follow_links = 0;
static int opt_apparent_size = 0;

/* -- DECLARATION OF FONCTION --- */
/**
 * Print parameters accept by mdu
 */
void usage (char *prog);
/**
 * Print du
 */
int du_file(const char *pathname);

/* --- FONCTION ---*/
void usage (char *prog) {
   printf("usage : %s [Lb] file", prog);
}

int du_file(const char *pathname) {
  static int nb_passage = 0;
  struct stat s;
  int test_s;

  if((strcmp(pathname,"..") == 0 || strcmp(pathname,".") == 0) && nb_passage != 0){
    return 0;
  }

  if(nb_passage == 0){
    nb_passage++;
  }

  if(opt_follow_links == 0){
    test_s = lstat(pathname, &s);
  }else{
    test_s = stat(pathname, &s);
  }
  assert(test_s != -1);

  /* pathname is file */
  if(S_ISREG(s.st_mode)){
    if(opt_apparent_size == 0){
      return s.st_blocks;
    }else{
      return s.st_size;
    }
  }

  /* pathname is a directory */
  if(S_ISDIR(s.st_mode)){
    DIR *dir;
    struct dirent *d;
    int file_size = 0;

    dir = opendir(pathname);
    assert(dir != NULL);

    if(opt_apparent_size == 0){
      file_size += s.st_blocks;
    }else{
      file_size += s.st_size;
    }

    while((d = readdir(dir))){
      if((strcmp(pathname,"..") != 0 && strcmp(pathname,".") != 0) == 0){
        file_size += du_file(d->d_name);
      }
    }
    return file_size;
  }
  return EXIT_FAILURE;
}

int main (int argc, char *argv[]) {
  char param;
  int res;

  if(argc<2){
    usage(argv[0]);
    return EXIT_FAILURE;
  }

  while((param = getopt(argc,argv, "bL")) != -1){
    switch (param) {
      case 'b':
        opt_apparent_size += 1;
        break;
      case 'L' :
        opt_follow_links += 1;
        break;
      default:
        usage(argv[0]);
    }
  }
  argc -= optind;
  argv += optind;
  assert(argc == 1);

  printf("Taille de %s -> %i\n",argv[0],(res = du_file(argv[0])));
  return EXIT_SUCCESS;
}
