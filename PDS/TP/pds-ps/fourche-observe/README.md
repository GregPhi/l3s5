#   `multif`, `course` et `observation`

Ce répertoire correspond à l’exercice `multif` de la section
[Clonage de processus](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdps-fork.html#multif)
et aux exercices de la section
[Gestion de processus](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdps-ps.html).

Ce répertoire devra contenir **exclusivement** les fichiers :
`README.md`, `Makefile`, `multif.c`, `course.c`, `observe.c` et
`tests.sh`.

*Compilation*

Makefile :
  * $ make multif
  * $ make course
  * $ make observe

OU :
  * $ make all

CLEAN :
  * $ make clean

*multif.c*

<code>
  int multif (func_t f[], char \*args[], int n);
  int testfunc(char \*chaine);
</code>


La fonction testfunc compare son argument avec :
  * "true" en retournant EXIT_SUCCESS
  * "false" en retournant EXIT_FAILURE
  * "sleep", exécute sleep(5) puis retourne EXIT_SUCCESS
  * sinon retourne EXIT_FAILURE

*course.c*

<code>
  int main(int argc, char \*argv[]);
</code>

Exécute une course entre dix participants, chaque participant affiche un message après un décompte de 100 millions d'implémentation d'une variable puis relance un décompte de 100 millions d'implémentation d'une variable.

Après avoir vérifié que chaque participant avait bien fini leur exécution et les avoir placé dans leur ordre d'arrivée dans un tableau, affiche l'ordre d'arrivée.

*oberve.c*

Pseudo-code:
<code>
  -> créer le un nombre de fils donné par l'utilisateur
  -> afficher leur pid pour savoir leur état
  -> exécuter la fonction sleep(5)
  ==> while(1){ sleep(5)  }
  -> exécution de la commande ps
  ==> system(ps)
  -> attendre la fin d'un fils et afficher son pid
  ==> wait(NULL)
  ==> printf et fflush
</code>

L'ajout d'une option pour la création des fils a été mise en place "-n" [le nombre de fork() à faire], car le nombre de fork() effectué par défault est au nombre de 5.

*Script*
Exécution :
  * $ chmod +x test.sh
  * $ ./test.sh
