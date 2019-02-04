#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

int vflag, rflag, wflag, xflag;

int displayVerbose();

void usage (char *prog) {
   printf("usage : %s [rvwx] file", prog);
}

int maccess (int argc, char **argv) {
  int mode = F_OK;
  int i;
  printf("OPTIONS verbose:%i, read:%i, write:%i, exec:%i\n",vflag,rflag,wflag,xflag);
  printf("REMAINING %i ARGS:\n",argc);
  for (i = 0; i < argc; i++) {
    printf("\t%s\n",argv[i]);
  }

  if(rflag){
    mode = mode | R_OK;
  }
  if(wflag){
    mode = mode | W_OK;
  }
  if(xflag){
    mode = mode | X_OK;
  }

  if(vflag){
    displayVerbose();
  }else{
    perror("Erreur utilisation des flags");
    exit(EXIT_FAILURE);
  }
  return access(argv[1],mode);
}

int displayVerbose(){
  switch(errno){
    case EACCES:
      printf("ERREUR EACCES, vous n'avez pas tous les accès nécessaires.\n");
      break;
    case ENAMETOOLONG:
      printf("ERREUR ENAMETOOLONG, le chemin est trop long.\n");
      break;
    case ENOENT:
      printf("ERREUR ENOENT, une partie du chemin n'existe pas.\n");
      break;
    case ENOTDIR:
      printf("ERREUR ENOTDIR, une partie du chemin n'est pas un répertoire.\n");
      break;
    case EROFS:
      printf("ERREUR EROFS, tentative d'écriture sur un fichier en lecture seule.\n");
      break;
    case ELOOP:
      printf("ERREUR ELOOP, liens symboliques rencontrés en boucle\n.");
      break;
    default:
      printf("ERREUR NON GEREE, voir l'erreur 'errno' pour obtenir le code d'erreur.\n");
  }
  return 1;
}

int main (int argc, char **argv) {
   int ch;
   vflag = 0;
   rflag = 0;
   wflag = 0;
   xflag = 0;
   while ((ch = getopt(argc, argv, "vrwxh")) != -1) {
      switch (ch) {
        case 'v':
           vflag = 1;
           break;
        case 'r':
           rflag = 1;
           break;
        case 'w':
           wflag = 1;
           break;
        case 'x':
           xflag = 1;
           break;
        case 'h':
        default:
           usage(argv[0]);
      }
   }
   argc -= optind;
   argv += optind;
   return maccess(argc,argv);
}
