package com.ecomapp.app;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;
@RestController
public class UserController {
    private List<User> userList=new ArrayList<>();

    @GetMapping("/api/users")
    public List<User> getAllUsers(){
        return userList;
    }
    @PostMapping("/api/users")
    public List<User> createUsers(@RequestBody User user){
        userList.add(user);
        return userList;
    }
}
