# Use zip to zip files in the target folder into 50M chunks
find "$1" -type f -size +50M | while IFS= read -r FILE; do
    zip -s 50m "${FILE%.*}.zip" "$FILE"
    rm "$FILE"
done