#!/bin/sh -uf

make

TRI_RAP=./tri-rapide
TRI_RAP_IN=/tmp/alea
FILE_T=res-tri-seuil-nbthread.dat
TRI_RAP_GP=tri-rapide-seuil-nbthread.gp
dd if=/dev/urandom of=/tmp/alea bs=262144 count=128 2> /dev/null

rm -f $FILE_T

for seuil in `awk 'BEGIN {for(i=1;i<=1048576;i*=2) print i}'`; do
    for nbthread in `awk 'BEGIN {for(i=2;i<=32;i*=2)}'`; do
  $TRI_RAP -T -n $nbthread -s $seuil $TRI_RAP_IN >> $FILE_T
  $TRI_RAP -T -n $nbthread -s $seuil $TRI_RAP_IN >> $FILE_T
  $TRI_RAP -T -n $nbthread -s $seuil $TRI_RAP_IN >> $FILE_T
  $TRI_RAP -T -n $nbthread -s $seuil $TRI_RAP_IN >> $FILE_T
    done
done

gnuplot -persist $TRI_RAP_GP

make realclean
