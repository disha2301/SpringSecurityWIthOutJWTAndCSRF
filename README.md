# 🔐 Spring Security API Demo without JWT and CSRF

A simple Spring Boot application demonstrating **Spring Security with role-based access control** using **in-memory authentication**.

## 🚀 Features

- Role-based access control (USER / ADMIN)
- In-memory user details management
- BCrypt password encoding
- Form login + HTTP Basic Authentication
- Three sample endpoints:
  - Public (no authentication)
  - Secured User access
  - Secured Admin access

---
## 📁 Project Structure

```
src/
└── main/
    ├── java/
    │   └── com/
    │       └── gevernova/
    │           └── springsecurity/
    │               ├── config/
    │               │   └── SecurityConfig.java
    │               ├── controller/
    │               │   └── UserController.java
    │               └── SpringsecurityApplication.java
    └── resources/
        └── application.properties
```
---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Security
- Maven
- Postman (for testing)

---
## 🔐 Default Users

| Username | Password         | Role  |
|---------|------------------|-------|
| `admin` | `admin_password` | ADMIN |
| `user`  | `user_password`  | USER  |

> Passwords are encoded using BCrypt internally.

---
## 📌 Endpoints & Access Control

| Endpoint               | Method | Access Type      | Role Required |
|------------------------|--------|------------------|---------------|
| `/api/public`          | GET    | Public           | None          |
| `/api/user`            | GET    | Secured          | USER          |
| `/api/admin`           | GET    | Secured          | ADMIN         |

---

## 📦 application.properties

```properties
spring.application.name=springsecurity
server.port=9090
```
## 🔧 How to Run

Clone the repository:

```bash
git clone https://github.com/your-username/spring-security-api.git
cd spring-security-api
```

Build and run the project:

```bash
./mvnw spring-boot:run
```

Or use your IDE to run `SpringsecurityApplication.java` manually.

---

## 🧪 API Testing (Postman)

| Endpoint               | Method | URL                                 | Auth Type  | Username | Password   |
|------------------------|--------|-------------------------------------|------------|----------|------------|
| Public API             | GET    | `http://localhost:9090/api/public`  |  None     | –        | –          |
| User API               | GET    | `http://localhost:9090/api/user`    |  Basic    | user2    | password2  |
| Admin API              | GET    | `http://localhost:9090/api/admin`   |  Basic    | admin    | password1  |

> 🔐 Use Postman's **Authorization tab → Basic Auth** to test secured routes.

---
## 🖥️ Browser-Based Form Login

In addition to API-based authentication (via Postman or tools like curl), this project also supports **form-based login**:

- Visit any protected endpoint (e.g. `http://localhost:9090/api/user`) in your browser
- You will be redirected to Spring Security’s default login form at:

  ```
  http://localhost:9090/login
  ```

- Enter one of the configured credentials:
  - **Username**: `user2`, **Password**: `user_password`
  - or
  - **Username**: `admin`, **Password**: `admin_password`

- After login, you’ll be redirected to:

  ```
  http://localhost:9090/api/private
  ```

> This behavior is controlled by `.formLogin().defaultSuccessUrl("/api/private", true)` in `SecurityConfig.java`.
---

## 🧠 Key Concepts

- `SecurityConfig.java`: Configures Spring Security, role-based access, password encoder, and filter chain.
- `UserController.java`: Defines REST endpoints for public, user, and admin access.
- `BCryptPasswordEncoder`: Hashes passwords securely.
- `SecurityFilterChain`: Handles form login, HTTP Basic auth, and role-based restrictions.

---

## 📦 Dependencies Used

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```
---
