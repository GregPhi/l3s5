#!/bin/sh

# PREPARATION
mkdir test
cd test
echo "42 is the answer of The Ultimate Question of Life" > test.txt
ln -s piege piege
cd ..

# TEST
echo test d existence
./maccess ./test/test.txt

echo

echo test de lecture
./maccess -rv ./test/test.txt

echo

echo test d écriture
./maccess -wv ./test/test.txt

echo

echo test d exécution
./maccess -xv ./test.sh

echo

echo ERREUR EACCES, vous n avez pas tous les accès nécessairesc
echo avec VERBOSE
chmod -x test
./maccess -xv ./test/test.txt
echo sans VERBOSE
./maccess -x ./test/test.txt
chmod +x test

echo

echo ERREUR ENAMETOOLONG, le chemin est trop long
./maccess -xv ./chemintroplongaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

echo

echo ERREUR ENOENT, une partie du chemin n existe pas
./maccess -xv ./test/repertoire/inexistant.txt

echo

echo ERREUR ENOTDIR, une partie du chemin n est pas un répertoire
./maccess -xv ./test/chemin.txt/fonctionne/pas.txt

echo

echo ERREUR EROFS, tentative d écriture sur un fichier en lecture seule

echo

echo ERREUR ELOOP, liens symboliques rencontrés en boucle
./maccess -xv ./test/piege

# NETTOYAGE
rm -rf test
