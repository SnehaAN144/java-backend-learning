package com.ecomapp.app;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();

    public List<User> FetchAllUsers() {
        return userList;
    }

    public List<User> AddUsers(User user) {
        userList.add(user);
        return userList;
    }

    public User fetchUser(Long id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
