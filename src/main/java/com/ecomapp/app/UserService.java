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

    public Optional<User> fetchUser(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    public boolean updateUser(Long id, User updatedUser) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    return true;
                })
                .orElse(false);
    }
}
