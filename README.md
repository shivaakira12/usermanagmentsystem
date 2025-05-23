# User Management System Demo

A Spring Boot demo application for managing users, featuring a `User` entity with basic fields like ID, first name, and email.

## Technologies Used

*   Spring Boot
*   Java
*   Maven

## Prerequisites

*   Java Development Kit (JDK)
*   Apache Maven

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Building

Use Maven to build the project:

```bash
mvn clean install
```

### Running

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

Alternatively, you can run the compiled JAR file located in the `target` directory:

```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```
*(Note: The JAR filename might vary based on your project version)*

## API Endpoints

The application provides the following API endpoint:

### Get All Users

`GET /api/users/getAllUsers`

Retrieves a paginated and sortable list of users.

#### Parameters

*   `page` (optional): The page number to retrieve (default: 0).
*   `size` (optional): The number of users per page (default: 5).
*   `sortBy` (optional): The field to sort by (default: `id`).
*   `direction` (optional): The sort direction (`asc` or `desc`, default: `asc`).

## Project Structure

*   `src/main/java`: Contains the main application code.
*   `src/main/java/com/ums/demo/entity`: Contains the JPA entity `User`.
*   `src/main/resources`: Contains configuration and static files.
*   `pom.xml`: Maven project file.

## Further Information

See the [HELP.md](HELP.md) file for more details and links to Spring Boot documentation. 