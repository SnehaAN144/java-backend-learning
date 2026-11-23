package com.ecomapp.app;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userservice;
    private long nextId = 1L;

    @GetMapping("/api/users")
    public List<User> getAllUsers(){
        return userservice.FetchAllUsers();
    }
    @PostMapping("/api/users")
    public void createUsers(@RequestBody User user){
        user.setId(nextId++);
        userservice.AddUsers(user);
    }
    @GetMapping("/api/users/{id}")
    public User getAllUsers(@PathVariable Long id){
        return userservice.fetchUser(id);
    }
}
