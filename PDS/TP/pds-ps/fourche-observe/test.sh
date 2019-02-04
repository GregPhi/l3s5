#!/bin/bash

path=$(pwd)

#COMPILATION
make all

echo "MULTIF"

echo "Exécution : $path/multif true false"
./multif true false
echo "Exécution : $path/multif true true"
./multif true true
echo "Exécution : $path/multif false false"
./multif false false
echo "Exécution : $path/multif false true"
./multif false true
echo "Exécution : $path/multif true"
./multif true
echo "Exécution : $path/multif false"
./multif false

echo -e

echo "Exéctuion : $path/course"
./course

echo -e

echo "Exéctuion : $path/oberve"
./observe

make clean
