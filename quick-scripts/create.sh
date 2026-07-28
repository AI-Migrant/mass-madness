#!/bin/sh
# Create a new private repository and clone it locally
if [ -z "$1" ]; then
  echo "Usage: sh create.sh [repo_name]"
  exit 1
fi

gh repo create "$1" --public --clone
mkdir "$1/audio"
mkdir "$1/graphics"
cp README.md "$1/"
git config --global --add safe.directory "/storage/emulated/0/Documents/gits/GitHub/$1"
git -C "$1" remote add origin "https://github.com/AI-Migrant/$1.git"
git -C "$1" add README.md
git -C "$1" commit -m "init"
git -C "$1" push --set-upstream origin main
