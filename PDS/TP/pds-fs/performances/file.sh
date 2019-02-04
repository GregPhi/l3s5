#!/bin/bash

path=$(pwd)
cat /dev/urandom | head -c 1 > $path/monfichier1.txt
cat /dev/urandom | head -c 10 > $path/monfichier10.txt
cat /dev/urandom | head -c 100 > $path/monfichier100.txt
cat /dev/urandom | head -c 1000 > $path/monfichier1000.txt
cat /dev/urandom | head -c 10000 > $path/monfichier10000.txt
cat /dev/urandom | head -c 100000 > $path/monfichier100000.txt
cat /dev/urandom | head -c 1000000 > $path/monfichier1000000.txt
