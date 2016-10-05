#!/bin/bash
tr -c '[:alnum:]' '[\n*]' < hamlet.txt | sort | uniq -c | sort -nr | head -30 | tail -29 > unmodified.dat
tr -c '[:alnum:]' '[\n*]' < hamlet.txt | egrep "^[[:lower:]]+$" | sort | uniq -c | sort -nr | head -30 | tail -29 > lowercase.dat

# TODO: Stopwords
# TODO: Stemmed text

general_gnuplot="set style fill solid border -1; set xtics rotate;"
gnuplot -p -e "$general_gnuplot set title 'Unmodified text'; plot 'unmodified.dat' using 1:xticlabels(2) with boxes"
gnuplot -p -e "$general_gnuplot set title 'Lowercase words'; plot 'lowercase.dat' using 1:xticlabels(2) with boxes"

