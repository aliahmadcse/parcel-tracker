# Parcel Tracker API

This is a Spring Boot-based REST API for managing parcels and clients. It includes full CRUD operations for clients and
parcels, supports status updates, and provides advanced search functionality. The `Client` module was developed using a
**Test-Driven Development (TDD)** approach.

---

## 🧪 Test-Driven Development (TDD)

The **Client module** (i.e., all Client APIs) was developed using the **TDD approach**. Unit/Integration tests were written **before
** the implementation to guide the design and ensure reliability.

---

## ✅ How to Run Tests

This is a **Maven** project. You can run all tests using the following command:

```bash
mvn clean test
```

## 🚀 How to Run the Project

- Clone the repo uisng `git clone https://github.com/aliahmadcse/parcel-tracker.git`
- Change directory `cd parcel-tracker`
- Run `docker compose up --build`
- Access the API using `http://localhost:8080/api-ui`

## ⚠️Assumptions and Limitations
- Each client must have a unique email address. Duplicate emails are not allowed during registration.
- Client-side pagination is supported through pageNo and recordPerPage.

## 💡Extra Features
- Unit and integration tests for the Client module (written using JUnit + Mockito).
- Dockerfile to containerize the application.
- docker-compose.yml to orchestrate the application and its dependencies.

## 📁 Tech Stack
- Java 21
- Spring Boot (3.4.4)
- Swagger / OpenAPI 3
- JUnit, Mockito
- Docker, Docker Compose
- MongoDB (Spring Data MongoDB)
