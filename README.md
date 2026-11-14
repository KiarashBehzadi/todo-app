# Todo App
## A simple RESTful API built with Spring Boot that allows users to create accounts and manage their personal todo lists.
### Each user can create and retrieve their own todos with different Priority levels.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven
- Bean Validation

## Features
- User registration
- Duplicate username prevention
- create todos for specific users
- retrieve all todos belonging to a user
- validate request data
- Global Exception Handling
- support Todo Priority: LOW, MEDIUM, HIGH

   
## API Endpoints

## 1. Create User
### Request

POST http://localhost:8080/users
### Body:
```json
    {
        "username":"kia",   
        "password":"k123@"
    }
```

### Response - 201 Created
    {
        "userId": 1,
        "username": "kia"
    }


### Request
POST http://localhost:8080/users

### Body
    {
        "username":"maria",
        "password":"marii1#"
    }
### Response
    {
        "userId": 3,
        "username": "maria"
    }

## 2. Create Todo (POST /users/{id}/todos)

### Request
POST http://localhost:8080/users/1/todos
###
    {
        "title":"Buy milk",
        "description":"2 liters of milk",
        "priority":"MEDIUM"
    }
### Response -201 Created
    {
        "todoId": 1,
        "title": "Buy milk",
        "description": "2 liters of milk",
        "priority": "MEDIUM",
        "completed": false
    }
### Request
POST http://localhost:8080/users/1/todos
###
    {
        "title":"Java Course",
        "description":"I should review and practice.. ",
        "priority":"HIGH"
    }
### Response
    {
            "todoId": 2,
            "title": "Java Course",
            "description": "I should review and practice.. ",
            "priority": "HIGH",
            "completed": false
        }


## GET All Todos of a User  
### for userId 1:
### Request
GET http://localhost:8080/users/1/todos
### Response
    [
        {
            "todoId": 1,
            "title": "Buy milk",
            "description": "2 liters of milk",
            "priority": "MEDIUM",
            "completed": false
        },
        {
            "todoId": 2,
            "title": "Java Course",
            "description": "I should review and practice.. ",
            "priority": "HIGH",
            "completed": false
        }
    ]





## Validation 
    
Request data is validated using Jakarta Bean Validation.

### Examples:
- Username must not be blank and must contain at least 3 characters,
- Password must not be blank and contain 4 to 8 characters,
- Todo title must not be blank and must contain at least 3 characters,
- Todo priority is required and must be a valid enum value (LOW, MEDIUM, or HIGH).

## Error Handling

Global Exception Handling is implemented using `@RestControllerAdvice`

| Exception | Http Status | Descrption
| --- | --- | --- |
| `UserNotFoundException` | 404 Not Found | The requested user does not exist. |
| `UserAlreadyExistsException` | 409 Conflict | The username is already taken. |
| `MethodArgumentNotValidException` | 400 Bad Request | The request data failed validation. |


## Duplicate Username

### Request

POST /users

{
  "username": "randy",
  "password": "1234"
}

### Response — 409 Conflict

User already exists: randy

## User Not Found

### Request

GET /users/22/todos

### Response — 404 Not Found

User not found: 22

## How to Run

1. Clone the Repository
2. Create a PostgreSQL database
3. configure your database credentials in application.properties.
4. Run the application using your IDE or Maven

   ``mvn spring-boot:run``

the application runs at:  http://localhost/8080




