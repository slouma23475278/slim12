# 📆 Medilink Microservices

Cette base contient une architecture microservices Spring Boot avec :

- Eureka Server (service discovery)
- API Gateway (routage des APIs)
- Service `rendez-vous` (CRUD de rendez-vous)
- Services `consultation`, `notification`, `ordenance`, `paiement`, `user` (enregistrés dans Eureka)

---

## ✅ Pré-requis

- Java 17
- Maven 3.9+

---

## 🚀 Ordre de démarrage recommandé

Depuis chaque dossier de microservice :

1. `microservices/eureka-server`
2. `microservices/rendez-vous`
3. `microservices/consultation`
4. `microservices/notification`
5. `microservices/ordenance`
6. `microservices/paiement`
7. `microservices/user`
8. `microservices/gateway/getway`

Exemple :

```bash
mvn spring-boot:run
```

---

## 🔗 Endpoints disponibles (service rendez-vous)

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

Valeurs `status` : `PENDING`, `CONFIRMED`, `CANCELED`.

---

## 🌐 Ports

- Eureka Server: `8761`
- Gateway: `8560`
- Rendez-vous: `8030`
- Consultation: `8040`
- Notification: `8050`
- Ordenance: `8060`
- Paiement: `8070`
- User: `8081`

---

## 📄 License

MIT License
