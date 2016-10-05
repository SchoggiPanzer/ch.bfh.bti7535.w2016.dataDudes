#!/bin/bash

content=`< $1`
IFS=' '; 
for word in $content; do 
    echo "$word"
done
