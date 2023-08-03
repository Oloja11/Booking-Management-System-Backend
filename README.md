<H1>Booking System Documentation</H1>

<p>Introduction
This document provides a comprehensive guide to the implementation 
of app user registration, email verification, login, logout , VTU 
functionality. These features are essential for creating a
secure and user-friendly web application for user to purchase airtime 
The implementation adheres to professional best practices and security
standards to ensure a robust and reliable user authentication syste</p>

<H1>Table of Contents</H1>
Technologies used
<br/>
Setup
<br/>
User Registration
<br/>
User Login
<br/>
User Logout
<br/>
Security Configuration
<br/>
Services
<br/>


1. Technologies Used:<br/>Java, MySQL for database storage, JSON Web Tokens (JWT) for secure user authentication
   Express Validator for input validation.
2. Setup
   Clone the repository and navigate to the project directory.
   Configure the database connection to connect to your local workbench

3. User Registration
   A user can register on the application by providing a valid email address and a strong password. And other details
4. User Login
   Registered users can log in using their verified email and password.
   Upon successful login, a JSON Web Token (JWT) is generated and sent to the client.
   The JWT is used for subsequent authenticated requests to protected routes.
5. User Logout
   Users can log out of the application, which invalidates the JWT and requires re-authentication.
6. Security Considerations
   Passwords are never stored in plaintext, they are encoded
7. Make sure your application is up and running after installation of all the dependencies 
8. Use a tool like Postman, or a web browser to interact with the endpoints. For example , to perform a login , send a POST request
   to `http://localhost:8083/api/booking-mgt/v1/auth/login` with appropriate credentials in the request body 
   if this request is successful.. Congratulations! You've successfully set up and run the Spring Boot application with the 