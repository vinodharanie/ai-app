# AI App – Software Engineering Learning Path Generator

A Spring Boot REST API that generates **personalized learning paths** using **Spring AI + OpenAI** based on tech stacks.  

📺 Reference Video: [YouTube Tutorial](https://www.youtube.com/watch?v=wExWF6VtN2E&t=965s)

---

## Table of Contents

1. [Prerequisites](#prerequisites)  
2. [Project Setup](#project-setup)  
3. [Database Configuration](#database-configuration)  
4. [OpenAI API Key Setup](#openai-api-key-setup)  
5. [Environment Configuration](#environment-configuration)  
6. [Building the Project](#building-the-project)  
7. [Running the Application](#running-the-application)  
8. [Testing with REST Client](#testing-with-rest-client)  
9. [Project Structure](#project-structure)  

---

## Prerequisites

- **JDK 21+** → `java -version`  
- **Maven 3.8+** → `mvn -version`  
- **MySQL 8+** → `mysql --version`  
- **Git & VS Code**

---

## Project Setup

```bash
git clone <repository-url>
cd ai-app
```

---

## Database Configuration

Start MySQL and create the database:

```bash
# macOS
brew services start mysql

# Linux
sudo systemctl start mysql
```

```sql
CREATE DATABASE ai_app_db;
USE ai_app_db;
```

**Connection Details:**  
- Host: `localhost`  
- Port: `3306`  
- Database: `ai_app_db`  
- User: `root`  
- Password: `your-password`

---

## OpenAI API Key Setup

1. Sign up at [OpenAI](https://platform.openai.com/)  
2. Generate a secret API key  
3. Check usage/limits: [OpenAI Billing](https://platform.openai.com/billing/overview)

> ⚠️ OpenAI API calls are billable. Monitor usage to avoid unexpected charges.

---

## Environment Configuration (.env)

Create a `.env` file in project root:

```properties
MYSQL_URL=jdbc:mysql://localhost:3306/ai_app_db?useSSL=false&serverTimezone=UTC
MYSQL_USERNAME=root
MYSQL_PASSWORD=your-password
OPEN_AI_KEY=sk-your-api-key
```

> ⚠️ Never commit `.env` to Git

---

## Building the Project

**Using Maven Wrapper:**

```bash
./mvnw clean install
```

**Using Maven (if installed globally):**

```bash
mvn clean install
```

Build artifacts: `target/ai-app-0.0.1-SNAPSHOT.jar`

---

## Running the Application

**Run Dev Server:**

```bash
./mvnw spring-boot:run
```

**Or run JAR directly:**

```bash
java -jar target/ai-app-0.0.1-SNAPSHOT.jar
```

Server: `http://localhost:8080`

---

## Testing with REST Client

Use `requests.http` in VS Code:

- GET all: `/software-engineering`  
- GET by ID: `/software-engineering/{id}`  
- POST new: `/software-engineering`  
- PUT update: `/software-engineering/{id}`  
- DELETE: `/software-engineering/{id}`  

> Update `@baseUrl` in `requests.http` if needed

---

## Project Structure

```
src/main/java/com/ai/first/app/ai_app/
├─ model/SoftwareEngineering.java
├─ repository/SoftwareEngineeringRepository.java
├─ service/SoftwareEngineeringService.java, AiService.java
├─ controller/SoftwareEngineeringController.java
├─ config/EnvConfig.java
```

**Key Points:**  
- `Service` → business logic + AI integration  
- `Controller` → REST endpoints  
- `Model` → JPA entity

