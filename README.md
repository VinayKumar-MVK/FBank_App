# FBank_App
Banking app using Spring Boot , Spring Data JPA, Spring Boot MVC

# 🏦 Banking Application

## 📌 Project Description

The **Banking Application** is a web-based application developed using Java Spring Boot. It provides basic banking functionalities such as creating bank accounts, managing customer details, checking account information, and performing banking operations.

This project demonstrates the implementation of Spring Boot, Spring MVC, Spring Data JPA, Hibernate, and MySQL for developing a backend banking management system.

## 🚀 Technologies Used

- **Java** – Programming Language
- **Spring Boot** – Backend Framework
- **Spring MVC** – Web Application Architecture
- **Spring Data JPA** – Database Operations
- **Hibernate** – ORM Framework
- **MySQL** – Relational Database
- **Maven** – Dependency Management
- **Eclipse IDE** – Development Environment

## ✨ Features

- Create new bank accounts.
- View bank account details.
- Update customer and account information.
- Delete bank accounts.
- Manage banking records.
- Perform database operations using Spring Data JPA.
- Integrate MySQL database with Spring Boot.

## 🏗️ Project Architecture

The application follows a layered architecture:

1. **Controller Layer** – Handles HTTP requests and responses.
2. **Service Layer** – Implements business logic.
3. **Repository Layer** – Performs database operations using Spring Data JPA.
4. **Entity Layer** – Defines database entities.
5. **Database Layer** – Stores banking information using MySQL.

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/VinayKumar-MVK/FBank_App.git
```

### 2. Open the Project

Import the project into Eclipse IDE as an existing Maven project.

### 3. Configure MySQL Database

Update your `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 4. Run the Application

Run the main Spring Boot application class:

```java
@SpringBootApplication
public class BankingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingAppApplication.class, args);
    }
}
```

### 5. Access the Application

Open your browser and access:

```text
http://localhost:8080
```

## 📂 Project Structure

```text
BankingApp
│
├── src/main/java
│   └── com.example.banking
│       ├── controller
│       ├── service
│       ├── repository
│       ├── entity
│       └── BankingAppApplication.java
│
├── src/main/resources
│   ├── application.properties
│   └── templates
│
├── src/test
│
├── pom.xml
└── README.md
```

## 🎯 Learning Outcomes

- Understanding Spring Boot application development.
- Implementing Spring MVC architecture.
- Performing CRUD operations using Spring Data JPA.
- Connecting Spring Boot with MySQL.
- Understanding Hibernate ORM.
- Developing layered architecture in Java applications.

## 👨‍💻 Developer

**Vinay Kumar**

GitHub: https://github.com/VinayKumar-MVK

---

⭐ If you find this project useful, don't forget to star the repository!
