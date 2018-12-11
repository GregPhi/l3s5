#!/bin/bash

# Philippot Grégoire

mkdir bin

jflex src/exsujet0.lex

javac -cp src -d bin src/ExSujet0.java

echo "Exécution du fichier data.txt"
java -cp bin ExSujet0 data.txt

echo -e "\nCréation automate"
jflex -dot src/exsujet0.lex
dot -T pdf -O dfa-big.dot
dot -T pdf -O dfa-min.dot
dot -T pdf -O nfa.dot

rm -f dfa-big.dot
rm -f dfa-min.dot
rm -f nfa.dot
rm -rf bin
rm -f src/Yylex.java
rm -f src/Yylex.java~
