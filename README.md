### Booking System Documentation

### Introduction

This document provides a comprehensive guide to the implementation
of app user registration, email verification, login, logout ,Booking services. These features are essential for creating
a
secure and user-friendly web application for user to book an appointment in a business
The implementation adheres to professional best practices and security
standards to ensure a robust and reliable user authentication system

### Table of Contents

- [Getting Started](#Get_started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Project](#running-the-project)
- [Usage](#usage)
- [Endpoints](#endpoints)

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and
testing purposes.

### Prerequisites

- [Java](https://www.java.com/) (version 17 )
- [Maven](https://maven.apache.org/) (version 3.9.9 or higher)
- [MySql](https://www.mysql.com/) (version 8.0.0 or higher)

### Installation

1. Technologies Used:<br/>Java, MySQL for database storage, JSON Web Tokens (JWT) for secure user authentication
   Express Validator for input validation.
2. Setup
   Create a MySQL database and update the database connection details in the `application.yml` file.
3. Build the project using Maven:

### Running the Project

# 1. install Docker

docs on windows: https://docs.docker.com/docker-for-windows/install/

youtube video for windows: https://www.youtube.com/watch?v=XgRGI0Pw2mM

on mac: https://docs.docker.com/docker-for-mac/install/

youtube video for mac: https://www.youtube.com/watch?v=-y1BmDbcaEU

on linux: https://docs.docker.com/engine/install/ubuntu/

# 2. run the following command in the root directory of the project

```bash
docker-compose up 
```

after few minutes the application will be up and running

3. open the browser and go to http://localhost:8080/swagger-ui/index.html
4. use the swagger ui to test the application
5. to stop the application run the following command in the root directory of the project

```bash
docker-compose down
```

```
The application will start and be available at `http://localhost:8083`. You can access the API endpoints using your preferred HTTP client, such as [Postman](https://www.postman.com/) or `curl`.

### API Endpoints

Here are the available API endpoints:

| Method | Endpoint                                                           | Description                                                              |
|--------|--------------------------------------------------------------------|--------------------------------------------------------------------------|
| POST   | api/booking-mgt/v1/user/sign-up                                    | This will allow the user to sign up                                      |
| POST   | api/booking-mgt/v1/auth/login                                      | This will allow the user to login                                        |
| POST   | api/booking-mgt/v1/auth/logout/{email}                             | logout by providing your email as user                                   |
| POST   | api/booking-mgt/v1/business                                        | Create Business as a Business owner                                      |
| POST   | api/booking-mgt/v1/service-offering                                | Create a service offering                                                |
| POST   | api/booking-mgt/v1/service-offering/book/{serviceId}               | Book a service with specific service Id                                  |
| POST   | api/booking-mgt/v1/service-offering/accept/{serviceId}/{userEmail} | Accept a service with specific service Id and User Email                 |
| POST   | api/booking-mgt/v1/service-offering/book/{serviceId}               | Book a service with specific service Id                                  |
| POST   | api/booking-mgt/v1/service-offering/cancel/{serviceId}/{userEmail} | cancel a service with specific service Id and userEmail                  |
| GET    | api/booking-mgt/v1/service-offering/{page}/{size}                  | Get all service  offering by passing number of pages and size            |
| GET    | api/booking-mgt/v1/service-offering//{userEmail}/{page}/{size}     | Get all service  offering by userEmail, passing number of pages and size |



