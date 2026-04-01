# E-Commerce Backend Project Status & Result Report

## 1. Project Overview
This project is a fully functional E-commerce RESTful API backend built using **Spring Boot (Java)**. It implements comprehensive features for managing an online store, ensuring scalable code architecture and solid best practices.

**Key Technologies Used:**
*   **Framework:** Spring Boot 3.x
*   **Security:** Spring Security with JWT (JSON Web Tokens)
*   **Database:** H2 Database (In-Memory for easy testing) with Hibernate/JPA
*   **Build Tool:** Maven

## 2. Implemented Features
The backend has been completely built with the following modules:
1.  **JWT Authentication & Authorization**: Secure user registration and login, with role-based access control (User vs. Admin).
2.  **Product Management**: APIs for browsing and managing products.
3.  **Shopping Cart System**: Endpoints to add, update, and remove items from the shopping cart.
4.  **Order Processing**: Converting a cart checkout into an order in the database.
5.  **Payment Integration Placeholder**: Services simulating downstream payment providers (e.g., Stripe logic scaffolding).

## 3. API Endpoints Configuration

### 🔐 Authentication (`/api/auth`)
*   `POST /api/auth/signup`: Register a new user.
*   `POST /api/auth/login`: Authenticate and receive a JWT Bearer token.

### 🛍️ Products (`/api/products`) - *Publicly Accessible*
*   `GET /api/products`: Retrieve all products in the catalog.
*   `GET /api/products/{id}`: Fetch product details by ID.
*   `POST /api/products` *(Admin Only)*: Add a new product.

### 🛒 Cart (`/api/cart`) - *Requires JWT Token*
*   `GET /api/cart`: View the authenticated user's current cart.
*   `POST /api/cart/add`: Add an item to the cart.
*   `PUT /api/cart/update`: Update the quantity of a cart item.
*   `DELETE /api/cart/remove/{productId}`: Remove an item entirely from the cart.

### 📦 Orders (`/api/orders`) - *Requires JWT Token*
*   `POST /api/orders`: Place an order using the current items in the cart.
*   `GET /api/orders`: View order history for the authenticated user.

## 4. Execution Output & Status
**Status:** ✅ **SUCCESS: Running successfully.**

The application successfully compiles and boots up on `http://localhost:8080` without any errors. 
The database is automatically pre-seeded on startup with test data. The following test credentials can test both User & Admin flows immediately upon running:

**Sample User Account**
*   **Username:** `testuser`
*   **Password:** `password123`

**Sample Admin Account**
*   **Username:** `admin`
*   **Password:** `admin123`

## 5. Instructions for Running Locally
1. Clone the repository natively.
2. In the project root, run the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Test the endpoints using **Postman** or **cURL**. For any endpoint labelled as requiring a JWT token, use the `POST /api/auth/login` endpoint above to receive your token, then attach it to subsequent requests via the `Authorization: Bearer <your_token>` header.

## 6. Actual Application Outputs
To demonstrate the functional completion of the project, here are some live outputs captured directly from the running local application right now:

### 1. Successful Authentication (POST `/api/auth/login`)
When a user authenticates, the application correctly verifies credentials against its database and generates a standard JSON Web Token:
```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0dXNlciIsImlhdCI6MTc3...",
  "id": 1,
  "username": "testuser",
  "roles": [
    "ROLE_USER"
  ]
}
```

### 2. Admin Creates a Product (POST `/api/products`)
A user with `ROLE_ADMIN` successfully submits a new product to the database payload, showing our Entity mappings are functional:
**Request Body:** `{"name":"High-End Laptop", "description":"Premium model", "price":1500.0, "stockQuantity":10}`
**Response Output:**
```json
{
  "id": 1,
  "name": "High-End Laptop",
  "description": "Premium model",
  "price": 1500.0,
  "stockQuantity": 10,
  "imageUrl": null
}
```

### 3. Fetching User Cart (GET `/api/cart`)
Securely pulling the authenticated user's current shopping cart, demonstrating relational links between `User`, `Cart`, and empty `CartItem` arrays:
```json
{
  "id": 1,
  "user": {
    "id": 1,
    "username": "testuser",
    "password": "$2a$10$ldecoeVpnyEx/3SIbCDerua1Gp9/hQqolraW...",
    "role": "ROLE_USER"
  },
  "items": []
}
```
