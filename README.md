# Reddit Clone API

This is a backend REST API built with **Spring Boot** that mimics core functionality of Reddit — allowing users to create and fetch subreddits, register, log in, and post content securely using **JWT authentication**.

## 🔧 Tech Stack

- Java 21
- Spring Boot 3.4.5
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Lombok
- Maven

## 📦 Features

- User Registration and Login
- JWT-based Authentication
- Create and View Subreddits
- Secure APIs with role-based access
- Validations and Exception Handling

## 📌 Endpoints

| Method | Endpoint               | Description                     | Auth Required |
|--------|------------------------|---------------------------------|----------------|
| POST   | `/api/auth/signup`     | Register a new user             | ❌ No          |
| POST   | `/api/auth/login`      | Authenticate and get JWT        | ❌ No          |
| POST   | `/api/subreddit`       | Create a subreddit              | ✅ Yes         |
| GET    | `/api/subreddit`       | List all subreddits             | ❌ No          |

## ▶️ Running the App

Make sure MySQL is running and update your `application.properties` accordingly. Then run:

```bash
mvn spring-boot:run
