#!/bin/bash

path=$(pwd)

make

echo "mtail sans option"
$path/mtail $path/test.txt

echo "mtail avec option n = 15"
$path/mtail 15 $path/test.txt

make clean
