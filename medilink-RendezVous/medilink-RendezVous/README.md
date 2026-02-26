# 📆 Medilink Microservices (Version améliorée)

Ce dépôt contient une architecture distribuée avec :

- Microservices Spring Boot (`rendez-vous`, `notification`, `consultation`, `ordenance`, `paiement`, `user`)
- Serveur de découverte Eureka
- API Gateway
- Serveur de configuration Spring Cloud Config
- Communication inter-MS via Feign (`rendez-vous` -> `notification`)
- Microservice additionnel **Node.js + PostgreSQL** (`lab-node-service`)
- Base de sécurité Gateway compatible Keycloak (activable)
- Docker Compose pour lancer l’écosystème principal

---

## ✅ Pré-requis

- Java 17
- Maven 3.9+
- Node.js 20+ (pour `lab-node-service` en local sans Docker)
- Docker + Docker Compose (option recommandé)

---

## 🚀 Démarrage local (Maven)

Ordre recommandé :

1. `microservices/config-server`
2. `microservices/eureka-server`
3. `microservices/notification`
4. `microservices/rendez-vous`
5. `microservices/consultation`
6. `microservices/ordenance`
7. `microservices/paiement`
8. `microservices/user`
9. `microservices/gateway/getway`

Commande standard dans chaque dossier :

```bash
mvn spring-boot:run
```

### Lancer le microservice Node + PostgreSQL

```bash
# PostgreSQL (Docker rapide)
docker run --name medilink-pg -e POSTGRES_DB=medilink -e POSTGRES_USER=medilink -e POSTGRES_PASSWORD=medilink -p 5432:5432 -d postgres:16

# Node service
cd microservices/lab-node-service
npm install
node index.js
```

---

## 🐳 Démarrage dockerisé

```bash
docker compose up --build
```

Fichier: `docker-compose.yml`.

---

## 🔐 Sécurité (Keycloak)

La sécurité Gateway est prête mais désactivée par défaut.

- `security.keycloak.enabled=false` (par défaut)
- Pour activer: passer à `true` et démarrer Keycloak (`http://localhost:8180`)
- Issuer URI configuré pour le realm `medilink`

---

## 🔗 Endpoints clés

### Service rendez-vous

```http
GET    /rendezvous
GET    /rendezvous/{id}
POST   /rendezvous
PUT    /rendezvous/{id}
DELETE /rendezvous/{id}
```

Exemple payload :

```json
{
  "date": "2026-02-25T10:30:00",
  "status": "CONFIRMED"
}
```

### Notification (utilisé par Feign)

```http
GET /notification/ping
```

### Node service

```http
GET  /node-health
GET  /notes
POST /notes
```

---

## 🌐 Ports

- Config Server: `8888`
- Eureka Server: `8761`
- Gateway: `8560`
- Rendez-vous: `8030`
- Consultation: `8040`
- Notification: `8050`
- Ordenance: `8060`
- Paiement: `8070`
- User: `8081`
- Node service: `8090`
- PostgreSQL: `5432`
- Keycloak: `8180`

---

## 📄 License

MIT License
