# Enterprise Banking Backend System

A production-ready RESTful Banking API built with Java, Spring Boot, Spring Data JPA, and MySQL following clean architecture and enterprise backend development best practices. The application provides secure and scalable banking operations through well-structured REST APIs and demonstrates modern backend development concepts.

## Features

- Secure Authentication using JWT
- Role-Based Authorization (Admin & User)
- Customer Management
  - Create Customer
  - Update Customer
  - Delete Customer
  - Get Customer by ID
  - Get All Customers
- Account Management
  - Create Account
  - Update Account
  - Delete Account
  - Get Account by ID
  - Get All Accounts
- Fund Transfer Between Accounts
- Transaction History
- Beneficiary Management
- Bean Validation
- Global Exception Handling
- Standardized API Responses
- Password Encryption using BCrypt
- Pagination, Sorting & Filtering
- Audit Fields (Created & Updated Timestamps)
- RESTful API Design
- Layered Architecture
- DTO Pattern
- Spring Data JPA & Hibernate ORM
- MySQL Database Integration
- Swagger/OpenAPI Documentation

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate

### Database
- MySQL

### Build Tool
- Maven

### API Testing
- Postman

### Documentation
- Swagger / OpenAPI

## Project Architecture

```text
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
MySQL Database
```

The project follows a layered architecture to ensure clean separation of concerns, maintainability, and scalability.

## Database Design

### Entities
- User
- Customer
- Account
- Transaction
- Beneficiary

### Relationships
- One Customer → Many Accounts
- One Account → Many Transactions
- One Customer → Many Beneficiaries

## Security

- JWT Authentication
- Spring Security
- BCrypt Password Encoding
- Role-Based Access Control (RBAC)
- Protected REST Endpoints

## API Documentation

Interactive API documentation is available through Swagger UI after running the application.

## Getting Started

### Clone Repository

```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/enterprise-banking-system.git
```

### Configure Database

Create the following file:

```text
src/main/resources/application-local.properties
```

Add your local database credentials:

```properties
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Run the Application

```bash
mvn spring-boot:run
```

## Project Highlights

- Enterprise-level project structure
- RESTful API development
- Clean Code principles
- SOLID principles
- Dependency Injection
- Database Relationships
- Secure Authentication & Authorization
- Exception Handling
- Validation
- Production-oriented architecture

## Author

Muhammad Ammar
