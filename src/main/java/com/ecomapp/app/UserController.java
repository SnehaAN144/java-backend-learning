package com.ecomapp.app;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userservice;

    @GetMapping("/api/users")
    public List<User> getAllUsers(){
        return userservice.FetchAllUsers();
    }
    @PostMapping("/api/users")
    public String createUsers(@RequestBody User user){
        userservice.AddUsers(user);
        return "users added successfully!";
    }
}
