package com.example.task_app.repository;
import com.example.task_app.model.User;
import com.example.task_app.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends UserService {

    private final List<User> users = new ArrayList<>();
    private Long userIdSequence = 1L; // Simulate an auto-increment ID.

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(userIdSequence++);
        users.add(user);
        return user;
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public Optional<User> findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public boolean deleteById(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}
