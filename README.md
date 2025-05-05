# Movie Catalog API

## Overview

The Movie Catalog API is a Spring Boot application that allows users to manage movies, their directors, and ratings. It provides basic CRUD operations (Create, Read, Update, Delete) for managing movie data.

## Features

- **Movies**: Add, update, delete, and retrieve movie details.
- **Directors**: Movies are linked to directors, and you can view the director of each movie.
- **Ratings**: Movies have associated ratings that can be added, updated, and retrieved.
- **API Documentation**: A Postman collection is provided for easy testing of the API.

## Technologies Used

- **Spring Boot**: Java-based framework to create stand-alone, production-grade Spring-based applications.
- **JPA & Hibernate**: For persistence management and ORM.
- **MySQL**: Relational database to store movie, director, and rating data.
- **Postman**: Used for API testing. A Postman collection is included for easy testing.

## Project Structure

movie-catalog/
├── docs/
│ └── postman-collection.json # Postman collection for testing the API
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ ├── com/
│ │ │ │ ├── kukri/
│ │ │ │ │ ├── moviecatalog/
│ │ │ │ │ │ ├── controller/ # API endpoints
│ │ │ │ │ │ ├── model/ # Movie, Director, Rating models
│ │ │ │ │ │ ├── repository/ # Database interactions
│ │ │ │ │ │ └── service/ # Business logic
│ └── resources/
│ └── application.properties # Database configurations and application properties
└── README.md # This file


## Setup & Installation

### Prerequisites

Make sure you have the following tools installed on your system:

- **Java** (Version 8 or higher)
- **Maven** (For building the application)
- **H2** (Database for storing the movie data)
- **Postman** (Optional, for testing API endpoints)

### Steps

1. **Clone the repository**:

   ```bash
   git clone https://github.com/AkmalNazir/movie-catalog
   cd movie-catalog

2. Set up the database:

Create a H2 database called movie_catalog or update the application.properties file with your database credentials.

Example application.properties:

spring.datasource.url=jdbc:h2:file:./testdb;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

spring.jpa.hibernate.ddl-auto=update

3. Build and run the application:

Using Maven, run the following command:

mvn clean install
mvn spring-boot:run

4. Access the API:

The application will run on http://localhost:8080/. You can use Postman to interact with the API.

5. Testing with Postman
A Postman collection has been provided to test the API. To import the collection:

i. Open Postman.

ii. Click on "Import" and select the postman-collection.json file located in the docs/ directory.

iii. Once imported, you can start testing the API endpoints defined in the collection.

Endpoints:

GET /movies: Get all movies.

GET /movies/{id}: Get a movie by ID.

GET movies/search/by-director: Get movies by its director name

GET /movies/search/by-rating: Get movies above certain rating percentage

POST /movies: Create a new movie.

PUT /movies/{id}: Update an existing movie by ID.

DELETE /movies/{id}: Delete a movie by ID.
