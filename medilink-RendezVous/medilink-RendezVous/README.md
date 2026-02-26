# 📆 Medilink Microservices (Presentation-ready pack)

Ce dépôt contient une architecture distribuée avec :

- Microservices Spring Boot (`rendez-vous`, `notification`, `consultation`, `ordenance`, `paiement`, `user`)
- Serveur de découverte Eureka
- API Gateway
- Serveur de configuration Spring Cloud Config
- Communication inter-MS via Feign (`rendez-vous` -> `notification`)
- Microservice additionnel **Node.js + PostgreSQL** (`lab-node-service`)
- Sécurité Gateway compatible Keycloak (activable)
- Front-end React (`frontend`) qui consomme le back
- Docker Compose + scripts de smoke test

---

## ✅ Pré-requis

- Java 17
- Maven 3.9+
- Node.js 20+
- Docker + Docker Compose

---

## 🚀 Démarrage Docker (recommandé démo)

```bash
./scripts/run-docker-demo.sh
```

Puis vérifier :

```bash
./scripts/smoke-test.sh
```

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
10. `microservices/lab-node-service`
11. `frontend`

Dans chaque service Spring:

```bash
mvn spring-boot:run
```

Node service:

```bash
cd microservices/lab-node-service
npm install
node index.js
```

Front:

```bash
cd frontend
npm install
npm run dev
```

---

## 🔐 Sécurité (Keycloak)

La sécurité Gateway est prête mais désactivée par défaut.

- `security.keycloak.enabled=false` (par défaut)
- Pour activer: passer à `true` dans `gateway/application.properties`
- Issuer URI: `http://localhost:8180/realms/medilink`

---

## 🔗 Endpoints clés

### Service rendez-vous

```http
GET    /rendezvous
GET    /rendezvous/{id}
POST   /rendezvous
PUT    /rendezvous/{id}
DELETE /rendezvous/{id}
GET    /rendezvous/notification-health
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

### Front-end React

```http
GET http://localhost:4173
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
- Frontend: `4173`
- PostgreSQL: `5432`
- Keycloak: `8180`

---

## 📄 License

MIT License
