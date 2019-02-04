#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <assert.h>

struct gc_s{
  char *bloc;
  off_t taille;
  phtread_t tid;
  unsigned long res;
};

#define ARG ((struct gc_s *) args)

void *mt_compteur_gc(void *argc){
  ARG->res=compteur_gc(ARG->bloc,ARG->taille);
  return null;
}

unsigned long compteur_gc(char *bloc, unsigned long taille) {
    unsigned long i, cptr = 0;

    for (i = 0; i < taille; i++)
        if (bloc[i] == 'G' || bloc[i] == 'C')
            cptr++;

    return cptr;
}

int main(int argc, char *argv[]) {
    struct stat st;
    int fd;
    char *tampon;
    int lus;
    unsigned long cptr = 0;
    off_t taille = 0;
    struct timespec debut, fin;
    int nbthread = 0;
    int i;
    struct gc_s *resthread;

    assert(argc > 1);

    /* nb of thread */
    if(argc > 2){
      nbthread = atoi(argv[2]):
    }else{
      nbthread = 16;
    }
    assert(nbthread>0);
    resthread = (struct gc_s *) malloc(sizeof(struct gc_s) * nbthread);

    /* Quelle taille ? */
    assert(stat(argv[1], &st) != -1);
    tampon = malloc(st.st_size);
    assert(tampon != NULL);
    assert(nbthread<st.st_size);

    /* Chargement en mémoire */
    fd = open(argv[1], O_RDONLY);
    assert(fd != -1);
    while ((lus = read(fd, tampon + taille, st.st_size - taille)) > 0)
        taille += lus;
    assert(lus != -1);
    assert(taille == st.st_size);
    close(fd);

    /* Calcul proprement dit */
    assert(clock_gettime(CLOCK_MONOTONIC, &debut) != -1);
    for(i = 0; i < nbthread; i++){
      resthread[i].bloc=tampon+i*(taille/nbthread);
      resthread[i].taille=taille/nbthread;
      pthread_create(&(resthread[i].tid), NULL, mt_compteur_gc, resthread+i);
    }
    if((taille%(nbthread*(taille/nbthread)))!=0){
      cptr+=compteur_gc(tampon+(nbthread*(taille/nbthread)), (nbthread*(taille/nbthread)%taille));
    }
    for(i = 0; i < nbthread; i++){
      pthread_join(resthread[i].tid, NULL);
      cptr+=resthread[i].res;
    }
    /*cptr = compteur_gc(tampon, taille);*/
    assert(clock_gettime(CLOCK_MONOTONIC, &fin) != -1);

    /* Affichage des résultats */
    printf("Nombres de GC:   %ld\n", cptr);
    printf("Taux de GC:      %lf\n", ((double) cptr) / ((double) taille));

    fin.tv_sec  -= debut.tv_sec;
    fin.tv_nsec -= debut.tv_nsec;
    if (fin.tv_nsec < 0) {
        fin.tv_sec--;
        fin.tv_nsec += 1000000000;
    }
    printf("Durée de calcul: %ld.%09ld\n", fin.tv_sec, fin.tv_nsec);
    printf("(Attention: très peu de chiffres après la virgule sont réellement significatifs !)\n");

    free(tampon);
    free(resthread);

    return 0;
}
