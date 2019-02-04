#   `maccess`

Ce répertoire correspond aux exercices de la section
[Vérifier les droits d’accès... et expliquer](http://www.fil.univ-lille1.fr/~hym/e/pds/tp/tdfs-cmd.html#access).

Ce répertoire devra contenir **exclusivement** les fichiers :
`README.md`, `Makefile`, `prlimit.c`, `maccess.c` et soit `tests.sh`
soit `session.txt`.
En particulier, un dépôt de code ne doit jamais contenir les fichiers
compilés (`.o`, exécutable, etc.).

Ce répertoire contient une version initiale de `maccess.c` pour
illustrer l’utilisation de `getopt` et ainsi vous aider à démarrer.

Vous éditerez ce fichier pour qu’il contienne un compte-rendu du
travail effectué (qu’est-ce qui marche, qu’est-ce qui ne marche pas,
etc.).

# Exécution du programme et du test
Afin de comipler maccess et prlimit, veuillez exécuter la commande dans le dossier ./access du projet :
  * $ make all

Puis afin d'exécuter le script des tests, vérifiez qu'il puisse être exécute. Si ce n'est pas le cas, veuillez exécuter la commande dans le dossier ./access du projet :
  * $ chmod +x test.sh
  * $ ./test.sh
  
## Exercice 1 : prlimit

L'exécution du main renvoi bien les valeurs attendues
 * NAME_MAX : longueur maximale d'un nom d'entrée dans le système de fichiers = 255
 * PATH_MAX : longueur maximale d'un chemin dans le système de fichiers = 4096


## Exercice 2 : maccess


## Exercice 3 : test
