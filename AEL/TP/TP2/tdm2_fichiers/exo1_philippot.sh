#!/bin/bash

# Grégoire PHILIPPOT

# EXERCICE 1

# - q1 : Affichez toutes les lignes du fichier contenant la chaîne « nez ».
echo \*\* Q1
grep -E --color=auto '\nez' "Cyrano.txt"
# grep -E -c --color=auto '\nez' "Cyrano.txt" --> ajout de l'option -c pour avoir le nombre de lignes obtenues

# - q2 : Affichez toutes les lignes du fichier contenant un mot ou un portion de phrase entre parenthèses.
echo \*\* Q2
grep -E --color=auto '\(.*\)' "Cyrano.txt"

# - q3 : Affichez toutes les lignes comportant un mot de longueur 5 exactement.
echo \*\* Q3
grep -E --color=auto '\<[[:alpha:]]{5}\>' "Cyrano.txt"

# - q4 : Observez le texte pour trouver ce motif et déduisez-en une commande grep qui affiche tous les vers de cette tirade commençant par un nom de style.
echo \*\* Q4
grep -E --color=auto "[[:upper:]]([[:lower:]]+)[[:space:]]\:[[:space:]]" "Cyrano.txt"
