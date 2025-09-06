# 🔐 Distributed Lock with Redis & Spring Boot

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-brightgreen?logo=springboot&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-7-red?logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?logo=docker&logoColor=white)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

A Spring Boot demo application showcasing **distributed locking with Redis and Redisson**.  
This project demonstrates how to protect critical operations (such as recalculations) from being executed concurrently across multiple instances using a **Redis-based distributed lock**.

---

## 🚀 Features

- 🔒 **Distributed locking** with [Redisson](https://github.com/redisson/redisson)
- 🗄 **Redis** as the lock storage backend
- 🌐 **REST API** with Swagger/OpenAPI documentation
- 🐳 **Docker Compose** setup for running Redis + the app together
- 📊 Clear logging of lock lifecycle (acquire, work, release)

---

## 📂 Project Structure

- **`LockController`** → REST controller exposing the `/recalc/{key}` endpoint
- **`LockedWorkService`** → Service layer handling lock acquisition & critical work
- **`OpenApiConfig`** → Swagger/OpenAPI config

---

## ⚙️ How It Works

1. Call `GET /recalc/{key}`.
2. App tries to acquire a **distributed lock** in Redis:
    - ✅ If lock is free → executes critical work
    - ❌ If lock is held → returns `Busy: lock is held`
3. Work simulation takes **10s**.
4. Lock automatically expires after **30s lease time** (if not released earlier).

---

## 🛠 Getting Started

### Prerequisites
- [Docker](https://www.docker.com/) & [Docker Compose](https://docs.docker.com/compose/)
- Java **17+**
- Gradle

### Run with Docker Compose
```bash
docker-compose up --build
