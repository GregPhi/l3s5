#!/bin/bash

# Grégoire PHILIPPOT

# EXERCICE 2

# - q1 : En reprenant et adaptant ce que vous avez fait pour le premier TP, écrivez un script shell qui définit une variable valeurAttribut contenant le motif des valeurs d’attributs XML.
# - - -  Ajoutez à votre script une commande egrep affichant (avec colorisation) toutes les lignes qui contiennent ce motif dans les fichiers du répertoire html
echo \*\* Q1
nomXML="[[:alpha:]:_][[:alnum:]:_.-]*"
refEntite="&$nomXML;"
valeurAttribut="\"([^\"<&]|$refEntite)*\""
grep -E -m 10 --color=auto "$valeurAttribut" ./html/*

# - q2 : Définissez de même des variables nomXML et refEntite Vous pourrez ensuite définir une variable baliseOuvrante en utilisant les variables précédentes.
# - - -  Testez cette expression en ajoutant une commande egrep affichant avec colorisation toutes les balises ouvrantes tenant sur une seule ligne dans les fichiers du dossier html.
echo \*\* Q2
baliseOuvrante="<$nomXML($nomXML*=$valeurAttribut)* *>"
grep -E -m 10 --color=auto "$baliseOuvrante" ./html/*

# - q3 : Observez quelles formes (il y en a plusieurs) prennent les numéros de téléphone figurant dans les fichiers du dossier html.
# - - -  Essayez d’extraire avec une seule commande grep tous les numéros de téléphone apparaissant dans les documents du répertoire. Le résultat affichera le nom du fichier parcouru puis les numéros qu’il contient. (utilisez la commande -o de egrep)
echo \*\* Q3
grep -E -m 10 --color=auto -o "(0|+33[[:space:]]\(0\)[[:space:]])[1-9](.[0-9]{2}){4}" ./html/*
