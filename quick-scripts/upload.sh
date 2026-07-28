#!/bin/sh
# ================================
# upload.sh
# Upload all files in a folder to a GitHub repo folder using REST API
# Usage:
#   sh upload.sh [LOCAL_FOLDER] [REPO_FOLDER]
# ================================
urlencode() {
    local s="$1"
    local i c hex
    for i in $(seq 1 ${#s}); do
        c=$(printf '%s' "$s" | cut -c "$i")
        case "$c" in
            [a-zA-Z0-9.~_-])
                printf '%s' "$c"
                ;;
            *)
                hex=$(printf '%02X' "'$c")
                printf '%%%s' "$hex"
                ;;
        esac
    done
}

# Fill in this information
# --------- Credentials ---------
GITHUB_TOKEN=""
REPO_OWNER=""
REPO_NAME=""
# -------------------------------

LOCAL_FOLDER="$1"
REPO_FOLDER="$2"

if [ -z "$LOCAL_FOLDER" ] || [ -z "$REPO_FOLDER" ]; then
  echo "Usage: sh upload.sh [LOCAL_FOLDER] [REPO_FOLDER]"
  exit 1
fi

if [ ! -d "$LOCAL_FOLDER" ]; then
  echo "Error: Local folder '$LOCAL_FOLDER' does not exist."
  exit 1
fi

if [ ! -d "logs" ]; then
  mkdir -p "logs"
  echo "Created logs folder."
fi

n=0
s=$(printf '{\n  "content":')
log="logs/log_$(date +%F).md"

for file in "$LOCAL_FOLDER"/*.b64; do
  [ -e "$file" ] || continue
  base_name=$(basename "$file" .b64)
  filename=$(urlencode "$base_name")
  commit_message='Upload "$base_name" via script'
  
  echo "---------------------------------------"
  printf "Attempting to upload: [%s]\t " "$base_name"
  curl -s -o /dev/null -w "%{http_code}" -H "$VERSION" -H "$AUTH" "$URL$filename"
  

  # 1. Build JSON payload
  {
    printf '{"message":"$commit_message", "content":"'
    tr -d '\r\n' < "$file"
    printf '"}'
  } > payload.json


  # 2. Upload to GitHub
  # remove '-s' to see curl's feedback
  RESPONSE=$(curl -s -X PUT \
    -H "X-GitHub-Api-Version:2026-03-10" \
    -H "Authorization: Bearer $GITHUB_TOKEN" \
    -H "Accept: application/vnd.github+json" \
    -H "Content-Type: application/json" \
    --data-binary @payload.json \
    "https://api.github.com/repos/$REPO_OWNER/$REPO_NAME/contents/$REPO_FOLDER/$filename")

  case "$RESPONSE" in
    "$s"*)
      n=$((n+1))
    ;;
  esac

  echo $(date) "\t$base_name" "\n$RESPONSE" "\n\n---\n" >> "$log"
done


if [ $n -le 1 ]; then
  echo "\nUploaded $n file."
else
  echo "\nUploaded $n files."
fi

# Clean up temp file
[ -f payload.json ] && rm payload.json