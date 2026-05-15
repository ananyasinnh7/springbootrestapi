# Spring Boot REST API

A production-ready REST API application built with Spring Boot, Spring Data JPA, and PostgreSQL. This project is designed for easy deployment on Cognizant infrastructure and follows industry best practices.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Database Configuration](#database-configuration)
- [Docker Deployment](#docker-deployment)
- [Deployment to Cognizant](#deployment-to-cognizant)
- [Troubleshooting](#troubleshooting)

## ✨ Features

✅ **Complete User Management System**
- Create, Read, Update, Delete (CRUD) user operations
- Email uniqueness validation
- Comprehensive error handling
- Request/Response validation

✅ **Production-Ready Architecture**
- Global exception handling with standardized error responses
- Lombok for reducing boilerplate code
- Service layer for business logic
- Repository pattern for data access
- Mapper pattern for DTO conversions

✅ **Database Management**
- PostgreSQL with Spring Data JPA
- Automatic schema generation and updates
- Transaction management
- Proper relationship handling

✅ **Docker Support**
- Multi-stage Docker build for optimized images
- Docker Compose for easy local development
- Health checks for both app and database

✅ **Comprehensive Logging**
- SLF4J with Logback
- Environment-specific logging levels
- Request/response logging capability

✅ **Security Best Practices**
- Input validation with Jakarta Validation
- SQL injection prevention via parameterized queries
- Sensitive data handling in production

## 🛠 Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Framework | Spring Boot | 3.2.0 |
| Language | Java | 17 |
| Build Tool | Maven | 3.9+ |
| Database | PostgreSQL | 15 |
| ORM | Hibernate (Spring Data JPA) | - |
| Validation | Jakarta Validation | - |
| Logging | SLF4J + Logback | - |
| Container | Docker | Latest |

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Java 17 or higher
- **Maven**: 3.9 or higher
- **PostgreSQL**: 12 or higher (for local development)
- **Docker & Docker Compose**: Latest stable versions (for containerized deployment)
- **Git**: For version control

### Verify Installation

```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check Docker version
docker --version
docker-compose --version
```

## 📁 Project Structure

```
springbootrestapi/
├── src/
│   ├── main/
│   │   ├── java/com/example/springbootrestapi/
│   │   │   ├── SpringBootRestApiApplication.java        # Main application class
│   │   │   ├── controller/
│   │   │   │   ├── UserController.java                 # User REST endpoints
│   │   │   │   └── HealthController.java               # Health check endpoint
│   │   │   ├── service/
│   │   │   │   └── UserService.java                    # Business logic
│   │   │   ├── repository/
│   │   │   │   └── UserRepository.java                 # Data access layer
│   │   │   ├── entity/
│   │   │   │   └── User.java                           # Database entity
│   │   │   ├── dto/
│   │   │   │   ├── UserRequest.java                    # API input DTO
│   │   │   │   └── UserResponse.java                   # API output DTO
│   │   │   ├── mapper/
│   │   │   │   └── UserMapper.java                     # DTO conversions
│   │   │   └── exception/
│   │   │       ├── GlobalExceptionHandler.java         # Error handling
│   │   │       ├── ResourceNotFoundException.java      # Custom exception
│   │   │       ├── ValidationException.java            # Custom exception
│   │   │       └── ErrorResponse.java                  # Error response DTO
│   │   └── resources/
│   │       ├── application.properties                  # Default configuration
│   │       ├── application-dev.properties              # Development config
│   │       └── application-prod.properties             # Production config
│   └── test/
│       └── java/com/example/springbootrestapi/
│           └── SpringBootRestApiApplicationTests.java  # Basic tests
├── docker/
│   ├── Dockerfile                                      # Container image
│   └── docker-compose.yml                              # Local deployment
├── pom.xml                                             # Maven configuration
├── .gitignore                                          # Git ignore file
└── README.md                                           # This file
```

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/ananyasinnh7/springbootrestapi.git
cd springbootrestapi
```

### 2. Configure Database (Local Development)

Create a PostgreSQL database for development:

```bash
# Connect to PostgreSQL
psql -U postgres

# Create database
CREATE DATABASE restapi_db;
CREATE DATABASE restapi_db_dev;

# Verify creation
\l

# Exit psql
\q
```

### 3. Update Application Properties (if using local PostgreSQL)

The default `application.properties` is configured for local PostgreSQL. If your PostgreSQL setup is different, update:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/restapi_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## 🏃 Running the Application

### Option 1: Using Maven

```bash
# Build the project
mvn clean install

# Run the application (default/dev profile)
mvn spring-boot:run

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

### Option 2: Using JAR File

```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/springbootrestapi-1.0.0.jar

# Run with specific profile
java -jar target/springbootrestapi-1.0.0.jar --spring.profiles.active=dev
```

### Option 3: Using Docker Compose (Recommended for Local Development)

```bash
# Build and start all services
docker-compose up --build

# Run in background
docker-compose up -d --build

# View logs
docker-compose logs -f app

# Stop services
docker-compose down
```

The application will be available at: `http://localhost:8080`

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Endpoints

#### 1. Create User
**POST** `/users`

Create a new user in the system.

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "9876543210",
  "address": "123 Main Street, City"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "9876543210",
  "address": "123 Main Street, City",
  "createdAt": "2026-05-15T10:30:00",
  "updatedAt": "2026-05-15T10:30:00"
}
```

#### 2. Get All Users
**GET** `/users`

Retrieve all users from the system.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "9876543210",
    "address": "123 Main Street, City",
    "createdAt": "2026-05-15T10:30:00",
    "updatedAt": "2026-05-15T10:30:00"
  }
]
```

#### 3. Get User by ID
**GET** `/users/{id}`

Retrieve a specific user by their ID.

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "9876543210",
  "address": "123 Main Street, City",
  "createdAt": "2026-05-15T10:30:00",
  "updatedAt": "2026-05-15T10:30:00"
}
```

#### 4. Update User
**PUT** `/users/{id}`

Update an existing user's information.

**Request Body:**
```json
{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "phone": "9876543211",
  "address": "456 Oak Avenue, City"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "phone": "9876543211",
  "address": "456 Oak Avenue, City",
  "createdAt": "2026-05-15T10:30:00",
  "updatedAt": "2026-05-15T11:45:00"
}
```

#### 5. Delete User
**DELETE** `/users/{id}`

Delete a user from the system.

**Response (204 No Content)**

#### 6. Health Check
**GET** `/health`

Check if the application is running.

**Response (200 OK):**
```json
{
  "status": "UP",
  "message": "Application is running successfully"
}
```

### Error Responses

#### 400 Bad Request
```json
{
  "status": 400,
  "message": "Validation failed",
  "error": "INVALID_INPUT",
  "timestamp": "2026-05-15T10:30:00",
  "path": "/api/users",
  "fieldErrors": [
    {
      "field": "email",
      "message": "Email should be valid"
    }
  ]
}
```

#### 404 Not Found
```json
{
  "status": 404,
  "message": "User not found with ID: 999",
  "error": "RESOURCE_NOT_FOUND",
  "timestamp": "2026-05-15T10:30:00",
  "path": "/api/users/999"
}
```

#### 409 Conflict (Email Already Exists)
```json
{
  "status": 400,
  "message": "User with email john@example.com already exists",
  "error": "VALIDATION_ERROR",
  "timestamp": "2026-05-15T10:30:00",
  "path": "/api/users"
}
```

## 💾 Database Configuration

### Database Schema

The application automatically creates the `users` table with the following structure:

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_users_email ON users(email);
```

### Configuration Profiles

- **Default (application.properties)**: General configuration
- **Development (application-dev.properties)**: `ddl-auto: create-drop`, detailed logging
- **Production (application-prod.properties)**: `ddl-auto: validate`, minimal logging, connection pooling

## 🐳 Docker Deployment

### Building Docker Image Manually

```bash
# Build the Docker image
docker build -f docker/Dockerfile -t springbootrestapi:1.0.0 .

# Run the container
docker run -d \
  --name restapi_app \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/restapi_db \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=postgres \
  springbootrestapi:1.0.0
```

### Using Docker Compose (Recommended)

```bash
# Start all services
docker-compose up -d

# Check status
docker-compose ps

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

## 🚀 Deployment to Cognizant

### Prerequisites for Cognizant Deployment

1. **Container Registry Access**: Access to Cognizant's container registry
2. **Kubernetes Cluster**: Access to Cognizant's Kubernetes infrastructure
3. **Configuration Management**: Environment-specific configuration setup

### Deployment Steps

1. **Build and Push Docker Image**

```bash
# Build the image
docker build -f docker/Dockerfile -t your-registry/springbootrestapi:1.0.0 .

# Push to registry
docker push your-registry/springbootrestapi:1.0.0
```

2. **Kubernetes Deployment**

Create a `k8s-deployment.yaml` file:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  spring.profiles.active: "prod"
  spring.datasource.url: "jdbc:postgresql://postgres-service:5432/restapi_db"

---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springbootrestapi
spec:
  replicas: 3
  selector:
    matchLabels:
      app: springbootrestapi
  template:
    metadata:
      labels:
        app: springbootrestapi
    spec:
      containers:
      - name: springbootrestapi
        image: your-registry/springbootrestapi:1.0.0
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          valueFrom:
            configMapKeyRef:
              name: app-config
              key: spring.profiles.active
        - name: SPRING_DATASOURCE_URL
          valueFrom:
            configMapKeyRef:
              name: app-config
              key: spring.datasource.url
        - name: SPRING_DATASOURCE_USERNAME
          valueFrom:
            secretKeyRef:
              name: db-credentials
              key: username
        - name: SPRING_DATASOURCE_PASSWORD
          valueFrom:
            secretKeyRef:
              name: db-credentials
              key: password
        livenessProbe:
          httpGet:
            path: /api/health
            port: 8080
          initialDelaySeconds: 40
          periodSeconds: 10
        readinessProbe:
          httpGet:
            path: /api/health
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 5
        resources:
          requests:
            memory: "256Mi"
            cpu: "250m"
          limits:
            memory: "512Mi"
            cpu: "500m"

---
apiVersion: v1
kind: Service
metadata:
  name: springbootrestapi-service
spec:
  selector:
    app: springbootrestapi
  ports:
  - port: 80
    targetPort: 8080
  type: LoadBalancer
```

Deploy to Kubernetes:

```bash
# Create secrets
kubectl create secret generic db-credentials \
  --from-literal=username=postgres \
  --from-literal=password=your-secure-password

# Deploy application
kubectl apply -f k8s-deployment.yaml

# Check deployment status
kubectl get deployments
kubectl get pods
kubectl get svc
```

3. **CI/CD Pipeline Integration**

The project is ready for integration with Cognizant's CI/CD pipelines:
- Maven builds are fully automated
- Docker images are optimized for registry storage
- Health checks are configured for readiness/liveness probes
- Environment variables support multi-environment deployments

## 🔧 Troubleshooting

### Issue: Database Connection Refused

**Solution:**
```bash
# Check if PostgreSQL is running
docker ps | grep postgres

# Or for local PostgreSQL
psql -U postgres -d restapi_db

# If using Docker Compose, restart PostgreSQL
docker-compose restart postgres
```

### Issue: Port 8080 Already in Use

**Solution:**
```bash
# Change the port in application.properties
server.port=8081

# Or kill the process using the port
lsof -ti:8080 | xargs kill -9
```

### Issue: Email Already Exists Error

**Solution:**
```sql
-- Clear existing data (development only)
DELETE FROM users;

-- Reset sequence
ALTER SEQUENCE users_id_seq RESTART WITH 1;
```

### Issue: Docker Build Fails

**Solution:**
```bash
# Clean Docker artifacts
docker system prune -a

# Rebuild with verbose output
docker build -f docker/Dockerfile --progress=plain -t springbootrestapi:1.0.0 .
```

### Issue: Cannot Connect to PostgreSQL from Docker

**Solution:**
- Use `postgres` service name instead of `localhost` in Docker Compose
- Set correct environment variables in docker-compose.yml
- Ensure services are on the same Docker network

## 📝 Sample curl Commands

```bash
# Create a user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210",
    "address": "123 Main St"
  }'

# Get all users
curl http://localhost:8080/api/users

# Get user by ID
curl http://localhost:8080/api/users/1

# Update user
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "email": "jane@example.com",
    "phone": "9876543211",
    "address": "456 Oak Ave"
  }'

# Delete user
curl -X DELETE http://localhost:8080/api/users/1

# Health check
curl http://localhost:8080/api/health
```

## 📄 License

This project is provided as-is for use on Cognizant infrastructure.

## 👨‍💻 Developer Information

**Created by:** ananyasinnh7  
**Date:** 2026-05-15  
**Language:** Java 17  
**Framework:** Spring Boot 3.2.0

## 📞 Support

For issues or questions regarding this application:
1. Check the Troubleshooting section above
2. Review Spring Boot documentation: https://spring.io/projects/spring-boot
3. Check PostgreSQL documentation: https://www.postgresql.org/docs/
4. Review Docker documentation: https://docs.docker.com/

---

**Happy Coding! 🚀**
