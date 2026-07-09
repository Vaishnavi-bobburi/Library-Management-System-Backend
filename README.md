# Library-Management-System-Backend
# Library Management System - Backend

## Overview

The Library Management System Backend is a RESTful web application built using **Spring Boot**. It provides APIs to manage books in a library, allowing users to perform CRUD (Create, Read, Update, Delete) operations. The project uses **Spring Data JPA** for database operations and **MySQL** as the relational database.

---

## Features

* Add a new book
* View all books
* View a book by ID
* Update book details
* Delete a book
* RESTful API architecture
* MySQL database integration
* Spring Data JPA for database access

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Maven
* Hibernate
* Eclipse IDE

---

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.example.librarymanagementbackend
│   │       ├── controller
│   │       ├── entity
│   │       ├── repository
│   │       ├── service
│   │       └── LibraryManagementBackendApplication.java
│   └── resources
│       └── application.properties
└── test
```

---

## Database Configuration

Update the database credentials in `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/librarydb
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8081
```

---

## API Endpoints

| Method | Endpoint          | Description           |
| ------ | ----------------- | --------------------- |
| GET    | `/api/books`      | Retrieve all books    |
| GET    | `/api/books/{id}` | Retrieve a book by ID |
| POST   | `/api/books`      | Add a new book        |
| PUT    | `/api/books/{id}` | Update book details   |
| DELETE | `/api/books/{id}` | Delete a book         |

---

## Sample JSON

### Add a Book

```json
{
  "id": 1,
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "genre": "Programming",
  "price": 650.00
}
```

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/library-management-backend.git
```

### 2. Open the Project

Import the project into Eclipse or your preferred Java IDE.

### 3. Configure MySQL

* Create a database named `librarydb`.
* Update the MySQL username and password in `application.properties`.

### 4. Run the Application

Run:

```
LibraryManagementBackendApplication.java
```

The server will start on:

```
http://localhost:8081
```

---

## Testing the APIs

You can test the REST APIs using:

* Postman
* Thunder Client (VS Code)
* Insomnia

---

## Future Enhancements

* User Authentication (JWT)
* Role-Based Access Control (Admin/User)
* Book Search and Filtering
* Pagination and Sorting
* Borrow and Return Book Management
* Fine Calculation
* API Documentation using Swagger/OpenAPI
* Docker Deployment
* Cloud Deployment (Render, Railway, AWS)

---

## Author

**Vaishnavi Bobburi**

* GitHub: https://github.com/Vaishnavi-bobburi
* LinkedIn: https://linkedin.com/in/vaishnavi-bobburi-a39a78310

---

## License

This project is developed for educational and learning purposes.
