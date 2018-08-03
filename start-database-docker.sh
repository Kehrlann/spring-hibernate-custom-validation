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

iterations=0
while (! psql -U postgres -h localhost --command "select 1;" hibernate_db > /dev/null 2>&1) && [ $iterations -lt 5 ]; do
  echo "Waiting for database to start ..."
  sleep 1;
  ((iterations++));
done

echo "Trying to seed database ..."
echo "(this might take a few minutes)"
if psql -U postgres -h localhost -f seeds.sql hibernate_db; then
  echo "Done !"
else
  echo "Database already seeded"
fi

echo "You database is up and seeded. Connect using:"
echo ""
echo "    psql -U postgres -h localhost hibernate_db"
echo ""
