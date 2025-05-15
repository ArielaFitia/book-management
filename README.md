#  Simple Book Management API

A lightweight REST API built with Spring Boot for managing books. This project demonstrates the implementation of CRUD operations with a clean architecture.

---
## Features
- Create Books: Add new books with a title and content.
- Read Books: Retrieve all books or a specific book by ID.
- Update Books: Modify the title or content of an existing book.
- Delete Books: Remove a book by its ID.
---
## Technologies Used
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA** (with H2 Database for development)
- **Lombok** (for boilerplate code reduction)
- **Swagger** for API documentation
- **JUnit** for testing
- **Maven** for dependency management
---
## Setup Instructions
Follow these steps to run the project on your machine:

1. Clone the repository:
   ```bash
   https://github.com/ArielaFitia/book-management.git
   cd book-management.git

2. Build the project:
   ```bash
   mvn clean install

3. Run the application:
   ```bash
   mvn spring-boot:run

4. Access the API:
   ```bash
   API Base URL: http://localhost:8080
5. Access Swagger Documentation:
    -Once the application is running, navigate to `http://localhost:8080/swagger-ui.html` to view and interact with the API documentation.

---

## API Documentation
The application uses Swagger to provide interactive API documentation. Visit `http://localhost:8080/swagger-ui.html` for details about the endpoints, request/response structures, and examples.

---

## Testing
The application includes unit tests for the service layer. Run the tests with:
```bash
   mvn test
```

---

## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests to improve the project.

---

