# 🔐 Secure Login & Registration Backend (2FA + PostgreSQL + BCrypt)

A Java Spring Boot-based backend that handles **user registration and login** with:

- ✅ Username/password authentication
- 🔒 Password hashing using **BCrypt**
- 📱 Two-Factor Authentication (2FA) via **OTP to mobile** (Twilio or equivalent)
- 🗃️ PostgreSQL database integration

---

## 🚀 Features

- 🔐 Secure user registration with encrypted passwords
- 🔑 Login authentication with 2FA (OTP sent to user's mobile)
- 🧵 Clean architecture using Spring Boot
- 🛡️ Defensive programming with exception handling
- 🗃️ PostgreSQL for user data persistence

---

## 📦 Tech Stack

| Layer        | Tech                |
|--------------|---------------------|
| Backend      | Java                |
| Security     | BCrypt, OTP (2FA)   |
| Database     | PostgreSQL          |
| Communication| Twilio (SMS OTP)    |
| Build Tool   | Maven               |

---

## 📁 Project Structure
src/
├── main/
│ ├── java/
│ │ └── com.example.loginbackend/
│ │ ├── controller/
│ │ ├── model/
│ │ ├── repository/
│ │ ├── service/
│ │ └── LoginBackendApplication.java
│ └── resources/
│ ├── application.properties
│ └── static/

Edit src/main/resources/application.properties:
# Database Config
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# Twilio Config
twilio.account_sid=your_account_sid
twilio.auth_token=your_auth_token
twilio.phone_number=your_twilio_number

