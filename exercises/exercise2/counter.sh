#!/bin/bash
tr -c '[:alnum:]' '[\n*]' < texts/hamlet.txt | sort | uniq -c | sort -nr | head -30 | tail -29 > dat/unmodified.dat
tr -c '[:alnum:]' '[\n*]' < texts/hamlet.txt | egrep "^[[:lower:]]+$" | sort | uniq -c | sort -nr | head -30 | tail -29 > dat/lowercase.dat

# TODO: Stopwords
# TODO: Stemmed text
tr A-Z a-z < texts/hamlet.txt > texts/hamletLow.txt
javac Stemmer.java
java Stemmer texts/hamletLow.txt
tr -c '[:alnum:]' '[\n*]' < texts/stemmedText.txt | sort | uniq -c | sort -nr | head -30 | tail -29 > dat/stemmed.dat

general_gnuplot="set style fill solid border -1; set xtics rotate;"
gnuplot -p -e "$general_gnuplot set title 'Unmodified text'; plot 'dat/unmodified.dat' using 1:xticlabels(2) with boxes"
gnuplot -p -e "$general_gnuplot set title 'Lowercase words'; plot 'dat/lowercase.dat' using 1:xticlabels(2) with boxes"
gnuplot -p -e "$general_gnuplot set title 'Stemmed text'; plot 'dat/stemmed.dat' using 1:xticlabels(2) with boxes"

