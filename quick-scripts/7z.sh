# Use 7 zip to zip files in the target folder into 50M chunks
find "$1" -type f -size +50M | while IFS= read -r FILE; do
    7z -bd -v50m a "${FILE%.*}.7z" "$FILE"
    rm "$FILE"
done