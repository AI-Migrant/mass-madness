#!/bin/sh
# Encode files in target folders 
INPUT_FOLDER="$1"
OUTPUT_FOLDER="$2"

if [ -z "$INPUT_FOLDER" ] || [ -z "$OUTPUT_FOLDER" ]; then
  echo "Usage: sh upload.sh [local_folder] [repo_folder]"
  exit 1
fi

if [ ! -d "$INPUT_FOLDER" ] || [ ! -d "$INPUT_FOLDER" ]; then
  echo "Error: Local folder '$INPUT_FOLDER' does not exist."
  exit 1
fi

mkdir -p "$OUTPUT_FOLDER"
n=0

for file in "$INPUT_FOLDER"/*; do
  if [ -f "$file" ]; then
    filename=$(basename "$file")
    base64 "$file" > "$OUTPUT_FOLDER/$filename.b64"
    echo "Encoded: $filename"
    n=$((n+1))
  fi
done

if [ $n -le 1 ]; then
  echo "Encoded" $n "file."
else
  echo "Encoded" $n "files."
fi
