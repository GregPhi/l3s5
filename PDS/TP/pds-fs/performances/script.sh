#!/bin/bash

path=$(pwd)

touch $path/log.log
chmod +w $path/log.log

$path/file.sh

echo -e "\n BUSIZE = 1"
$path/buf1.sh > $path/log.log

echo -e "\n BUSIZE = 16"
$path/buf16.sh > $path/log.log

echo -e "\n BUSIZE = 256"
$path/buf256.sh > $path/log.log

echo -e "\n BUSIZE = 1024"
$path/buf1024.sh > $path/log.log

make cleanFile
