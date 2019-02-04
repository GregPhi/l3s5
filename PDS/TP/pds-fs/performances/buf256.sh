#!/bin/bash

path=$(pwd)

make all

export MCAT_BUFSIZE
echo -e "\nMCAT_BUFSIZE=256"
MCAT_BUFSIZE=256 /usr/bin/time -f '%e %U %S' $path/mcat-scd $path/monfichier1.txt
MCAT_BUFSIZE=256 /usr/bin/time -f '%e %U %S' $path/mcat-scd $path/monfichier10.txt
MCAT_BUFSIZE=256 /usr/bin/time -f '%e %U %S' $path/mcat-scd $path/monfichier100.txt
MCAT_BUFSIZE=256 /usr/bin/time -f '%e %U %S' $path/mcat-scd $path/monfichier1000.txt
MCAT_BUFSIZE=256 /usr/bin/time -f '%e %U %S' $path/mcat-scd $path/monfichier10000.txt
MCAT_BUFSIZE=256 /usr/bin/time -f '%e %U %S' $path/mcat-scd $path/monfichier100000.txt
MCAT_BUFSIZE=256 /usr/bin/time -f '%e %U %S' $path/mcat-scd $path/monfichier1000000.txt

make clean
