
package controller;

import model.User;
import service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    private long nextId = 1L;

    // GET: Fetch all users
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.FetchAllUsers());
    }

    // POST: Create a new user
    @PostMapping("/api/users")
    public ResponseEntity<User> createUsers(@RequestBody User user) {

//        user.setId(nextId++);

        userService.AddUsers(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Optional<User>> getAllUsers(@PathVariable Long id) {
        return ResponseEntity.ok(userService.fetchUser(id));
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(
            @RequestBody User updatedUser,
            @PathVariable Long id) {

        boolean updated = userService.updateUser(id, updatedUser);

        if (updated) {
            return ResponseEntity.ok("User updated successfully");
        }

        return ResponseEntity.notFound().build();
    }
}
