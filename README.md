# AI App - Software Engineering Learning Path Generator

A Spring Boot REST API application that manages Software Engineering learning paths and uses Spring AI with OpenAI to automatically generate personalized learning paths based on tech stacks.

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

Before you begin, ensure you have the following installed:

### Required Software

1. **Java Development Kit (JDK) 21 or higher**
   - Download from: https://adoptium.net/ (OpenJDK)
   - Verify installation:
     ```bash
     java -version
     ```

2. **Maven 3.8.1 or higher** (This project includes Maven Wrapper, so this is optional)
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation:
     ```bash
     mvn -version
     ```

3. **MySQL Server 8.0 or higher**
   - macOS: `brew install mysql`
   - Linux: `sudo apt-get install mysql-server`
   - Windows: Download from https://dev.mysql.com/downloads/mysql/
   - Verify installation:
     ```bash
     mysql --version
     ```

4. **Git** (for version control)
   - Download from: https://git-scm.com/

5. **VS Code** (or any IDE of your choice)
   - Download from: https://code.visualstudio.com/

---

## Project Setup

### 1. Clone or Navigate to the Project

If cloning from a repository:
```bash
git clone <repository-url>
cd ai-app
```

Or if you already have the project:
```bash
cd /Users/vinol/Documents/AI-Learnings/ai-app
```

### 2. Project Structure

```
ai-app/
├── src/
│   ├── main/
│   │   ├── java/com/ai/first/app/ai_app/
│   │   │   ├── AiAppApplication.java          (Main Spring Boot Application)
│   │   │   ├── model/
│   │   │   │   └── SoftwareEngineering.java   (JPA Entity)
│   │   │   ├── repository/
│   │   │   │   └── SoftwareEngineeringRepository.java
│   │   │   ├── service/
│   │   │   │   ├── SoftwareEngineeringService.java
│   │   │   │   └── AiService.java
│   │   │   ├── controller/
│   │   │   │   └── SoftwareEngineeringController.java
│   │   │   ├── client/
│   │   │   │   └── AIService.java
│   │   │   └── config/
│   │   │       └── EnvConfig.java
│   │   ├── resources/
│   │   │   ├── application.yaml               (Spring Boot Configuration)
│   │   │   ├── static/                        (Static files)
│   │   │   └── templates/                     (HTML templates)
│   ├── test/
│   └── ...
├── pom.xml                                     (Maven Configuration)
├── .env                                        (Environment Variables)
├── requests.http                               (REST Client Requests)
├── README.md                                   (This file)
└── mvnw / mvnw.cmd                            (Maven Wrapper)
```

---

## Database Configuration

### Step 1: Start MySQL Server

**macOS:**
```bash
brew services start mysql
```

**Linux:**
```bash
sudo systemctl start mysql
```

**Windows:**
- MySQL should be running as a service. Check Services app.

**Verify MySQL is running:**
```bash
mysql -u root -p
```
(Enter your MySQL password or press Enter if no password set)

### Step 2: Create the Database

Open MySQL client:
```bash
mysql -u root -p
```

Enter your MySQL password (default is usually blank for local installations).

Run these SQL commands:
```sql
-- Create the database
CREATE DATABASE ai_app_db;

-- Verify database creation
SHOW DATABASES;

-- Select the database
USE ai_app_db;

-- Exit MySQL
EXIT;
```

### Connection Details

- **Host:** localhost
- **Port:** 3306 (default MySQL port)
- **Database:** ai_app_db
- **Username:** root
- **Password:** secret (or your MySQL root password)

---

## OpenAI API Key Setup

### Step 1: Create an OpenAI Account

1. Go to https://platform.openai.com/
2. Click "Sign up" and create a new account
3. Verify your email address

### Step 2: Generate an API Key

1. Log in to your OpenAI account
2. Click on your profile icon (top-right corner)
3. Select "API keys" from the dropdown
4. Click "Create new secret key"
5. Copy the generated key (you won't be able to see it again)
6. Save it securely

### Step 3: Check Your API Quota

1. Go to https://platform.openai.com/billing/overview
2. Check your usage and limits
3. Set up a spending limit if needed (recommended)

**Note:** OpenAI API calls are billable. Monitor your usage to avoid unexpected charges.

---

## Environment Configuration

### Create .env File

Create a `.env` file in the project root (`{path}/ai-app/.env`) with the following content:

```properties
# MySQL Database Configuration
MYSQL_URL=jdbc:mysql://localhost:3306/ai_app_db?useSSL=false&serverTimezone=UTC
MYSQL_USERNAME={username}
MYSQL_PASSWORD={password}

# OpenAI API Configuration
OPEN_AI_KEY=sk-your-actual-api-key-here
```

**Important:**
- Replace `sk-your-actual-api-key-here` with your actual OpenAI API key
- Replace `password` with your actual MySQL password if different
- Keep this file secure and never commit it to version control
- Add `.env` to `.gitignore` if using version control:
  ```bash
  echo ".env" >> .gitignore
  ```

## Building the Project

### Using Maven Wrapper (Recommended - No Maven installation needed)

```bash
cd {path}/ai-app
./mvnw clean install
```

### Using Maven (If installed globally)

```bash
mvn clean install
```

**What this does:**
- Cleans previous build artifacts
- Downloads dependencies
- Compiles Java source code
- Runs tests (if any)
- Packages the application into a JAR file

**Expected output:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXs
```

**Build artifacts location:**
- JAR file: `target/ai-app-0.0.1-SNAPSHOT.jar`
- Classes: `target/classes/`

---

## Running the Application

### Method 1: Using Spring Boot Maven Plugin (Recommended for Development)

```bash
./mvnw spring-boot:run
```

**Expected output:**
```
2026-03-10 15:00:00.000  INFO 12345 --- [main] 
com.ai.first.app.ai_app.AiAppApplication : Starting AiAppApplication
...
2026-03-10 15:00:05.000  INFO 12345 --- [main] 
o.s.b.w.embedded.tomcat.TomcatWebServer : Tomcat started on port(s): 8080
```

### Method 2: Running the JAR File Directly

First, build the project:
```bash
./mvnw clean install
```

Then run:
```bash
java -jar target/ai-app-0.0.1-SNAPSHOT.jar
```

### Using the requests.http File

The project includes a `requests.http` file with pre-configured API request examples.

**To send a request:**

1. Open `requests.http` file in VS Code
2. Hover over any request line and click "Send Request"
3. The response will appear in a split panel on the right

**Example requests in the file:**
- Get all records
- Get record by ID
- Create new records
- Update records
- Delete records

**To customize:**
- Edit the `@baseUrl` variable at the top to change the server address
- Modify the JSON request bodies as needed

---

## Project Structure Details

### Entity (Model)

**SoftwareEngineering.java**
- Represents a software engineering learning path record
- Fields: `id`, `name`, `techstack`, `learningPath`
- JPA entity mapped to the `software_engineering` table in MySQL

### Repository

**SoftwareEngineeringRepository.java**
- Spring Data JPA interface for database operations
- Provides CRUD methods (Create, Read, Update, Delete)

### Service

**SoftwareEngineeringService.java**
- Business logic layer
- Integrates with AIService to generate learning paths
- Methods:
  - `getAllSoftwareEngineering()` - Get all records
  - `getSoftwareEngineeringById(Long id)` - Get a single record
  - `createSoftwareEngineering(SoftwareEngineering)` - Create with AI-generated learning path
  - `updateSoftwareEngineering(Long id, SoftwareEngineering)` - Update with optional AI regeneration
  - `deleteSoftwareEngineering(Long id)` - Delete a record

### Controller

**SoftwareEngineeringController.java**
- REST API endpoint handler
- Maps HTTP requests to service methods
- Returns JSON responses

### AI Service

**AIService.java**
- Integrates with OpenAI's API using Spring AI
- Generates personalized learning paths based on tech stack
- Handles AI communication and error handling

### Configuration

**EnvConfig.java**
- Loads environment variables from `.env` file at application startup
- Makes environment variables accessible to the application
