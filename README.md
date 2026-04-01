# E-commerce Backend Assignment

This repository contains the backend implementation for an E-commerce platform built with Spring Boot.

## Features Included
1. **User Authentication & Authorization**: Registration and login functionality using JWT via Spring Security. Role-based authorization.
2. **Product Management**: Complete CRUD operations for products (Entities include: name, description, price, stock quantity, and image URL).
3. **Cart Management**: Add, update, and remove items dynamically. Users handle ONLY their own distinct cart.
4. **Order Processing**: Seamless cart checkout converting cart items into locked order records, validating constraints, and capturing shipping constraints.
5. **Payment Gateway Integration**: Mocked connection to Stripe utilizing `stripe-java`, resolving `PaymentIntent` interactions to generate safe test payment processes.

## Technology Stack
- Java 17+ (Compatible with JDK 24)
- Spring Boot 3.4.x
- Spring Security 6.x
- Spring Data JPA
- H2 In-Memory Database
- Stripe Java SDK
- JWT (io.jsonwebtoken)

## Running the Project
1. Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, or VS Code).
2. Start the Spring Boot Application by running `EcommerceBackendAssignmentApplication.java`.
3. The app will start on port `8080` typically.
4. **H2 Database Console** can be accessed at `http://localhost:8080/h2-console` with username `sa` and empty password.

## Testing
Core components have Mockito-driven JUnit tests located in `src/test/java/com/example/ecommerce/...`. Run `mvnw test` to execute the assertions.
