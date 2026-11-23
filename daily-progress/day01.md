
# Day 01 – Java Backend Development 🚀

**Date:** 23 November 2026  
**Focus:** Spring Boot Fundamentals & REST API Development

## 1. Project Setup

- Created a Spring Boot project using Spring Initializr.
- Opened and configured the project in IntelliJ IDEA.
- Understood the Maven project structure.
- Explored `pom.xml` for project dependencies.
- Explored `application.properties` for application configuration.
- Learned how to customize the server port using `server.port`.

## 2. Spring Boot Fundamentals

### `@RestController`
- Marks a class as a REST controller.
- Handles incoming HTTP requests.
- Returns response data directly, typically as JSON.

### HTTP Request Methods

| Annotation | Purpose |
|---|---|
| `@GetMapping` | Retrieve data |
| `@PostMapping` | Create or submit data |
| `@PutMapping` | Update data |
| `@DeleteMapping` | Delete data |

### `@RequestBody`
- Binds data from the HTTP request body to a method parameter.
- Maps incoming JSON data to a Java object.
- Commonly used when receiving data from Postman.

### `application.properties`
- Located inside `src/main/resources`.
- Used to configure Spring Boot application settings.
- Example: `server.port=1990` changes the default server port.
- Command-line arguments can override properties-file values.

## 3. Practical Implementation

### A. Hello POST API

Created a POST endpoint to receive a name from Postman.

```java
@PostMapping("/hello")
public String helloPost(@RequestBody String name) {
    return "Hello " + name + "!";
}
```

### B. User Model

Created `User.java` to represent user data.

**Attributes:**
- `id` – Unique user identifier.
- `firstName` – User's first name.
- `lastName` – User's last name.

Used Lombok's `@Data` to generate getters, setters and other common methods.

### C. User Management REST API

Created `UserController.java` to handle user-related requests.

| HTTP Method | Endpoint | Function |
|---|---|---|
| GET | `/api/users` | Fetch all users |
| POST | `/api/users` | Create a user |
| GET | `/api/users/{id}` | Fetch user by ID |
| PUT | `/api/users/{id}` | Update user |

- Used `List<User>` to maintain a collection of users.
- Used `ArrayList` for temporary in-memory storage.
- Tested APIs using Postman.

## 4. Layered Architecture

**Application Flow:**

Client → Controller → Service → Repository → Database

- **Controller:** Handles incoming HTTP requests.
- **Service:** Contains business logic.
- **Repository:** Handles database communication.
- **Database:** Stores application data.

Initially implemented the User Controller and User Service.

## 5. Service Layer & Dependency Injection

- Created `UserService.java` to separate business logic from the controller.
- Moved user operations and the user list into the service.
- Used `@Service` to mark the class as a Spring-managed component.

### Dependency Injection (DI)

Spring provides the required objects instead of manually creating them.

**Three approaches:**
1. `@Autowired` – Field injection.
2. Constructor Injection – Injecting dependencies through a constructor.
3. `@RequiredArgsConstructor` – Lombok generates a constructor for required fields.

Used constructor injection with Lombok's `@RequiredArgsConstructor`.

## 6. Automatic User ID Generation

**Problem:** When a user was created without an ID, the ID became `null`.

**Solution:** Generate IDs automatically in the service layer.

```java
private long nextId = 1L;

public void addUser(User user) {
    user.setId(nextId++);
    userList.add(user);
}
```

**Explanation:**
- `nextId` – Maintains the next available ID.
- `user.setId(nextId++)` – Assigns the current ID and increments it.
- Backend controls ID generation.
- Client-provided IDs are overridden.

**Important:** Unique identifiers should be generated and controlled by the backend, not API consumers.

## 7. Fetching a User by ID – `@PathVariable`

A Path Variable is a dynamic value passed through the URL.

Example: `GET /api/users/1`

```java
@GetMapping("/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
    // Fetch user by ID
}
```

- `{id}` represents the dynamic URL segment.
- `@PathVariable` binds the URL value to the method parameter.
- Service searches for the matching user.

## 8. Java Streams & Optional

Replaced the traditional `for` loop with Java Streams to find a user.

```java
public Optional<User> fetchUser(Long id) {
    return userList.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst();
}
```

**Stream Methods:**
- `stream()` – Converts a collection into a stream.
- `filter()` – Filters elements based on a condition.
- `findFirst()` – Returns the first matching element.
- `Optional<User>` – Represents a value that may or may not be present.

### Using Optional in Controller

```java
return userService.fetchUser(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
```

- `map()` – Converts a present user into a response.
- `ResponseEntity::ok` – Method reference for returning HTTP 200.
- `orElseGet()` – Provides an alternative response if no user is found.

## 9. ResponseEntity & HTTP Status Codes

`ResponseEntity` is used to wrap HTTP responses and control the response body and status code.

Package: `org.springframework.http`

### Common HTTP Status Codes

| Status Code | Meaning |
|---|---|
| 200 OK | Request successful |
| 201 CREATED | Resource created |
| 204 NO_CONTENT | Successful request without response body |
| 400 BAD_REQUEST | Invalid request |
| 401 UNAUTHORIZED | Authentication required |
| 403 FORBIDDEN | Access denied |
| 404 NOT_FOUND | Resource not found |
| 500 INTERNAL_SERVER_ERROR | Server error |

### Examples

```java
return ResponseEntity.ok(user);
```

```java
return ResponseEntity.notFound().build();
```

**API Behaviour:**
- Existing user → `200 OK` with user details.
- Non-existing user → `404 NOT_FOUND`.

## 10. `@RequestMapping` Annotation

- An annotation available in Spring MVC.
- Maps HTTP requests to controller methods.
- Can be used at method level and class level.

### A. Method-Level Mapping

`@GetMapping` is a shortcut for:

```java
@RequestMapping(
    value = "/api/users",
    method = RequestMethod.GET
)
```

Both perform the same operation.

**RequestMethod Enum includes:**
- GET
- POST
- PUT
- PATCH
- DELETE
- HEAD
- OPTIONS
- TRACE

### B. Class-Level Mapping

- Defines a common base URL for all endpoints in a controller.
- Avoids repeating the same URL in every method.
- Improves code readability.

Example:

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        // Fetch all users
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser() {
        // Fetch user by ID
    }

    @PostMapping
    public ResponseEntity<?> createUser() {
        // Create user
    }
}
```

### URL Mapping

| Class-Level Path | Method-Level Path | Final Endpoint |
|---|---|---|
| `/api/users` | Empty | `/api/users` |
| `/api/users` | `/{id}` | `/api/users/{id}` |

## 11. Testing & Debugging

- Tested GET and POST endpoints using Postman.
- Sent JSON request bodies and checked API responses.
- Understood HTTP 405 – Method Not Allowed.
- Learned that HTTP methods must match controller mappings.
- Used `ResponseEntity` for appropriate HTTP status codes.
- Tested fetching users by ID.
- Observed that in-memory data resets when the application restarts.

## 12. Git & GitHub

- Initialized a local Git repository.
- Connected the project to GitHub.
- Created the first commit.
- Successfully pushed the Spring Boot project to GitHub.

## 13. Key Takeaways

1. Understood the basic structure of a Spring Boot application.
2. Learned how REST controllers handle HTTP requests.
3. Understood `@RequestBody` and JSON mapping.
4. Implemented GET and POST endpoints.
5. Understood Controller and Service layer separation.
6. Practiced Dependency Injection and Constructor Injection.
7. Implemented automatic user ID generation.
8. Used `@PathVariable` to fetch users by ID.
9. Practiced Java Streams and Optional.
10. Used `ResponseEntity` and HTTP status codes.
11. Understood class-level and method-level `@RequestMapping`.
12. Tested REST APIs using Postman.
13. Practiced Git and GitHub version control.

## 14. Next Learning Goals

- Continue implementing and testing CRUD operations.
- Review PUT and DELETE endpoint functionality.
- Improve API response handling.
- Introduce the Repository layer.
- Connect the application to a database.
- Understand database persistence.

---

**Day 01 Status: Completed ✅**

*Consistency is the key to becoming a better backend developer.* 🚀
