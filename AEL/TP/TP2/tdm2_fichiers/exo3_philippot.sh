#!/bin/bash

# Grégoire PHILIPPOT

# EXERCICE 3

# - q1 : La deuxième colonne contient le numéro (au sein de la voie). En utilisant egrep, affichez exactement les adresses ayant un numéro qui comporte le chiffre 4 et qui se termine par BIS ou par TER (ex : 40BIS,341TER, ...)
echo \*\* Q1
grep -E -m 10 --color=auto "[^,4]*4[^,4]*(BIS|TER)" "bano-59009.csv"
#grep -E -c --color=auto "[^,4]*4[^,4]*(BIS|TER)" "bano-59009.csv" --> nombre de lignes

# - q2 : La troisième colonne contient le nom de la voie. Affichez exactement les adresses dont le nom de voie comporte le mot « Ascq »
echo \*\* Q2
grep -E -m 10 --color=auto "([^,]*,){2}[^,]*(Ascq)[^,]*(,[^,]*){5}" "bano-59009.csv"

# - q3 : Sélectionnez les adresses dont le nom de voie ne comporte aucune lettre minuscule.
echo \*\* Q3
grep -E -m 10 --color=auto "(.*,){2}[[:upper:][:punct:][:space:]]*,(.*,){3}.*" "bano-59009.csv"
