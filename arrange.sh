#!/bin/bash

for file in files/*; do
    if [ -f "$file" ]; then
        filename=$(basename "$file")
        first_char="${filename:0:1}"
        lowercase_char=$(echo "$first_char" | tr '[:upper:]' '[:lower:]')

        if [[ "$lowercase_char" =~ [a-z] ]]; then
            mv "$file" "$lowercase_char/"
        fi
    fi
done