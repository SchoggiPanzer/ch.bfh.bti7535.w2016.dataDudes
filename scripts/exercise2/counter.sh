#!/bin/bash
tr -c '[:alnum:]' '[\n*]' < texts/hamlet.txt | sort | uniq -c | sort -nr | head -30 | tail -29 > unmodified.out
tr -c '[:alnum:]' '[\n*]' < texts/hamlet.txt | egrep "^[[:lower:]]+$" | sort | uniq -c | sort -nr | head -30 | tail -29 > lowercase.out

# TODO: Stopwords
# TODO: Stemmed text
tr A-Z a-z < texts/hamlet.txt > hamletLow.out
javac Stemmer.java
java Stemmer hamletLow.out
tr -c '[:alnum:]' '[\n*]' < texts/stemmedText.txt | sort | uniq -c | sort -nr | head -30 | tail -29 > stemmed.out

general_gnuplot="set style fill solid border -1; set xtics rotate;"
gnuplot -p -e "$general_gnuplot set title 'Unmodified text'; plot 'unmodified.out' using 1:xticlabels(2) with boxes"
gnuplot -p -e "$general_gnuplot set title 'Lowercase words'; plot 'lowercase.out' using 1:xticlabels(2) with boxes"
gnuplot -p -e "$general_gnuplot set title 'Stemmed text'; plot 'stemmed.out' using 1:xticlabels(2) with boxes"

rm *.out
