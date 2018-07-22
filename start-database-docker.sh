#!/usr/bin/env bash

set -e

docker pull postgres

echo "Launching DB container for development (port 5432) ..."
docker run \
    --rm \
    --name hibernate_db \
    -e POSTGRES_DB=hibernate_db \
    -p 5432:5432 \
    -d \
    postgres
