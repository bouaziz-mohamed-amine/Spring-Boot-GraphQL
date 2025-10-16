# Spring Boot + GraphQL

## 📚 Project Overview

This is a simple Spring Boot project demonstrating how to use GraphQL to query and manage relationships between entities. It models a **Book** and its **Author** with a **many-to-one** relationship, allowing clients to perform CRUD operations on both using GraphQL **queries** and **mutations**.

## ✨ Features

This project is organized into two main branches, each with distinct implementations:

### 🔹 `feature/v1-graphql-book-crud`
- Book CRUD operations using **static in-memory data**.
- No database integration.
- Simple GraphQL schema and resolvers.

### 🔹 `feature/v2-spring-data`
- Book and Author CRUD operations using **Spring Data JPA** with **PostgreSQL**.
- GraphQL schema supports relational mapping (many-to-one between Book and Author).
- Advanced features:
  - Pagination and sorting of books.
  - Fetch specific book by ID.
  - Author details with associated books.
  - Create, update, delete book mutations.
  - Dynamic resolution of `author` field in `Book` using `authorId`.

## 🛠 Technologies Used

- **Spring Boot** – Backend framework.
- **GraphQL** – API design and query language.
- **PostgreSQL** – Relational database used for persisting books and authors.
- **pgAdmin** – GUI tool for managing PostgreSQL.
- **Docker Compose** – Container orchestration tool to run PostgreSQL and pgAdmin together.
- **Spring Security** – Security framework used to handle authentication and authorization.
