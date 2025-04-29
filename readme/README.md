# Health Care Management System - Microservices Backend

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/your-repo)
[![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)](https://github.com/your-repo)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

> **A scalable and production-ready Microservices-based REST API system for Healthcare Management built with Spring Boot 3.x and Spring Cloud 2024.x**

---

## 📋 Table of Contents
- [About the Project](#about-the-project)
- [Architecture Overview](#architecture-overview)
- [Microservices Overview](#microservices-overview)
- [Infrastructure Components](#infrastructure-components)
- [Authentication and Authorization](#authentication-and-authorization)
- [Database](#database)
- [Communication Flow](#communication-flow)
- [System Diagram](#system-diagram)
- [Future Enhancements](#future-enhancements)
- [Getting Started](#getting-started)
- [Contributing](#contributing)
- [License](#license)

---

## 📖 About the Project

This project manages healthcare operations including patients, doctors, appointments, billing, user management, and notifications through a modern distributed system architecture.

---

## 🏗 Architecture Overview

- Built using **Spring Boot**, **Spring Cloud**, **Spring Security OAuth2**.
- Microservices architecture with independent deployability.
- Centralized configuration and service discovery.
- Single shared MySQL Database (`health_care_db`).

---

## 📦 Microservices Overview

| Microservice | Description |
|:---|:---|
| `api-gateway` | Gateway routing and security |
| `auth-service` | OAuth2 authentication/authorization |
| `user-service` | User management |
| `patient-service` | Patient management |
| `doctor-service` | Doctor management |
| `appointment-service` | Appointments handling |
| `billing-service` | Billing and invoicing |
| `notification-service` | Email/SMS notifications |
| `file-service` | File uploads/downloads |

---

## ⚙️ Infrastructure Components

| Component | Purpose |
|:---|:---|
| `Config Server` | Centralized configuration management |
| `Eureka Server` | Service discovery and registration |
| `RabbitMQ` | Async communication (notification events) |
| `Grafana + Loki (Planned)` | Monitoring and centralized logging |
| `MySQL` | Central database for all microservices |

---

## 🛡 Authentication and Authorization

- OAuth2 Authorization Server (`auth-service`)
- API Gateway enforces token validation
- All microservices validate access tokens

---

## 🗄 Database

- Single MySQL Database: `health_care_db`
- Each microservice owns its own set of tables

---

## 🔄 Communication Flow

- Synchronous (REST) — between microservices using Feign Clients
- Asynchronous (Messaging) — using RabbitMQ for events (Appointment → Notification)

---

## 🗺 System Diagram

> See full [Architecture Diagram](#system-diagram) (built using Mermaid.js)

---

## 🚀 Future Enhancements

- Integrate **Grafana + Loki** for centralized monitoring/logging
- Add **Circuit Breakers** with **Resilience4j**
- Scale services with Kubernetes
- Secure configs with **HashiCorp Vault**

---

## 🛠 Getting Started

1. Clone the repo
    ```bash
    git clone https://github.com/your-repo/healthcare-management-system.git
    ```

2. Run Config Server, Eureka Server, and RabbitMQ first

3. Then run each microservice individually:
    ```bash
    mvn spring-boot:run
    ```

4. Access Swagger APIs at:
    - `http://localhost:8080/swagger-ui.html` (via API Gateway)

---

## 🤝 Contributing

Contributions are always welcome!

Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

---
