#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
docker compose up --build -d

echo "Stack started. Useful URLs:"
echo "- Eureka: http://localhost:8761"
echo "- Gateway: http://localhost:8560"
echo "- Frontend: http://localhost:4173"
echo "- Keycloak: http://localhost:8180"
