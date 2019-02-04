#!/bin/bash

make

echo "do -a : une commande"
./do true || echo "coucou"
./do false && echo "coucou"
echo
echo "do -a : plusieurs commande"
./do true  true  || echo "Erreur: or true true a renvoyé false"
./do false false && echo "Erreur: or false false a renvoyé true"

echo
echo "do -o"
./do -o true  true  || echo "Erreur: or true true a renvoyé false"
./do -o false false && echo "Erreur: or false false a renvoyé true"

echo
echo "do -ac"
./do -ac true false xterm || echo "coucou"
./do -ac true true xterm || echo "coucou"
echo
echo "do -oc"
./do -oc true false xterm || echo "coucou"
./do -oc false true xterm || echo "coucou"
echo
echo "do -kc"
./do -kc true false xterm || echo "coucou"
./do -kc false true xterm || echo "coucou"

make realclean
