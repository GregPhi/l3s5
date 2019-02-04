set title "Temps d'exécution par rapport à la taille du fichier"
set logscale x
set xlabel "taille en lignes"
set logscale y
set ylabel "temps en s ou vitesse en octets/s"
set style data linespoints
plot "courbes.dat" using 1:2 title "BUFSIZE=1", \
     "courbes.dat" using 1:3 title "BUFSIZE=16", \
     "courbes.dat" using 1:4 title "BUFSIZE=256", \
     "courbes.dat" using 1:5 title "BUFSIZE=1024"
pause -1  "Hit return to continue"
