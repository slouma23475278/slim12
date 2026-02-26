#!/usr/bin/env bash
set -euo pipefail

echo "[1/5] Gateway route rendezvous"
curl -fsS http://localhost:8560/rendezvous >/dev/null

echo "[2/5] Feign scenario endpoint"
curl -fsS http://localhost:8030/rendezvous/notification-health

echo "[3/5] Notification service ping"
curl -fsS http://localhost:8050/notification/ping

echo "[4/5] Node health"
curl -fsS http://localhost:8090/node-health >/dev/null

echo "[5/5] Front reachable"
curl -fsS http://localhost:4173 >/dev/null

echo "Smoke test passed"
