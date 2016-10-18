#!/bin/bash
tr -c '[:alnum:]' '[\n*]' < hamlet.txt | sort | uniq -c | sort -nr | head -30 | tail -29 > tfd.out
gnuplot -p -e "plot 'out.dat' using 1:xticlabels(2) with boxes"

rm *.out

