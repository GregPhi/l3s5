#!/bin/sh -uf

make

COMPTEUR_GC=./compteur-gc
COMPTEUR_GC_IN=/tmp/genome.dna
FILE_T=res-compteur-gp.dat
COMPTEUR_GC_GP=compteur-gc.gp
GENE=./aleazard

rm -f $FILE_T

for size in `awk 'BEGIN {for (i=100;i<=100000000;i*=10) print i}'`; do
    $GENE $size > $COMPTEUR_GC_IN
    for nbthread in `awk 'BEGIN {for (i=1; i<=32; i*=2) print i}'`; do
  $COMPTEUR_GC $COMPTEUR_GC_IN $nbthread >> $FILE_T
  $COMPTEUR_GC $COMPTEUR_GC_IN $nbthread >> $FILE_T
  $COMPTEUR_GC $COMPTEUR_GC_IN $nbthread >> $FILE_T
  $COMPTEUR_GC $COMPTEUR_GC_IN $nbthread >> $FILE_T
    done
  done

gnuplot -persist $COMPTEUR_GC_GP

make realclean
