#!/bin/bash
tr -c '[:alnum:]' '[\n*]' < pg1524.txt | sort | uniq -c | sort -nr | head -30 | tail -29 > out.dat
gnuplot -p -e "plot 'out.dat' using 1:xticlabels(2) with boxes"

