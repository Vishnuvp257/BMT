### README

# Movie Ticket Booking System

This is a Spring Boot-based Movie Ticket Booking System. The application provides APIs to manage movies and bookings.

## Prerequisites

- Java 11 or later
- Maven 3.6.3 or later
- MySQL database

## Setup Instructions

### 1. Clone the Repository

```bash
git https://github.com/Vishnuvp257/BMT.git
cd BMT
```

### 2. Configure the Database

Update the `application.properties` file located in `src/main/resources/` with your MySQL database credentials.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Create the Database Schema

Use the provided SQL schema file to set up the database schema. The schema file is located in the `database` folder.


### 4. Build and Run the Application

Navigate to the project directory and run the following Maven command to build and start the application.

```bash
mvn spring-boot:run
```

The application will start and run on `http://localhost:8080`.

### 5. API Documentation

API documentation is available via Postman:

[Movie Ticket Booking API Documentation](https://documenter.getpostman.com/view/19465135/2sA3XLG4sp)

### Example Requests

#### Create a Movie

```json
POST /api/movies
{
    "title": "Example Movie"
}
```



#### Create a Booking

```json
POST /api/bookings
{
    "userId": 1,
    "showtimeId": 1,
    "numTickets": 2,
    "selectedSeatIds": "1,2"
}
```





