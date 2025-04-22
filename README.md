# Spring Boot + GraphQL

## Project Overview:
This is a simple Spring Boot project that demonstrates the use of GraphQL for querying and managing relationships between entities. The project models a **Book** and its **Author** with a one-to-one relationship, allowing users to fetch, create, update, and delete books and their respective authors using GraphQL **queries** and **mutations**.

## Features 

- **GraphQL API** for querying and mutating books and authors.
- Support for querying:
  - Books with **pagination** and **sorting**.
  - A specific book by its ID.
  - Authors and their details.
- Support for mutations:
  - Create a new book.
  - Update an existing book.
  - Delete a book.
- **Dynamic resolution** of `author` field in `Book` using `authorId`.

  
## Technologies Used

- **Spring Boot**: Backend framework.
- **GraphQL**: For API design and query language.
