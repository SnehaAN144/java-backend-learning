package com.ecomapp.app;
import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private List<User> userList = new ArrayList<>();
    private Long nextId=1L;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> FetchAllUsers() {
        return userRepository.findAll();
    }

    public void AddUsers(User user) {
        userRepository.save(user);
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
