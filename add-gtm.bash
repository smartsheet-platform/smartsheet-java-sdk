#!/usr/bin/env bash
set -e
set -x
set -o errexit

TARGET_DOCS_DIR="target/apidocs"
DOCS_DIR="docs"

INDEX_FILE="$DOCS_DIR/index.html"

rm -rf "$DOCS_DIR"
cp -r "$TARGET_DOCS_DIR" "$DOCS_DIR"

sed -E '/^<head>$/r src/doc/gtm-header.txt' "$INDEX_FILE" > "$INDEX_FILE.tmp" && mv "$INDEX_FILE.tmp" "$INDEX_FILE"

sed -E '/^<head>$/r src/doc/gtm-data-layer.txt' "$INDEX_FILE" > "$INDEX_FILE.tmp" && mv "$INDEX_FILE.tmp" "$INDEX_FILE"

# First match only
sed -E '/^<frameset cols="20%,80%" title="Documentation frame"/r src/doc/gtm-body.txt' "$INDEX_FILE" > "$INDEX_FILE.tmp" && mv "$INDEX_FILE.tmp" "$INDEX_FILE"
