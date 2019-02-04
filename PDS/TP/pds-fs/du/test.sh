#!/bin/bash

make all

mkdir test
echo "test/test mdu" >> test/test.txt

echo "test mdu" >> test.txt

ln -s test/test.txt linktest

echo "Test mdu"
./mdu test/
du test/

echo "Test mdu -b"
./mdu -b test/
du -b test/

echo "Test mdu -L"
./mdu -L test/
du -L test/

echo "Linktest"
./mdu linktest
du linktest

echo "Linktest -b"
./mdu -b linktest
du -b linktest

echo "Linktest -L"
./mdu -L linktest
du -L linktest

echo "test.txt"
./mdu test.txt
du test.txt

echo "test.txt -b"
./mdu -b test.txt
du -b test.txt

echo "test.txt -L"
./mdu -L test.txt
du -L test.txt

rm -rf test
rm linktest
rm test.txt
make clean
