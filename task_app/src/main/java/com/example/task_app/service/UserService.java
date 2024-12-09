package com.example.task_app.service;
import com.example.task_app.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;
public class UserService {

    private List<User> users = new ArrayList<>();
    private Long userIdSequence = 1L; // Simulate an auto-increment ID.

    public List<User> getAllUsers() {
        return users;
    }

    public User createUser(String username, String password, String email) {
        User user = new User();
        user.setId(userIdSequence++);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole("USER");
        users.add(user);
        return user;
    }

    public Optional<User> findUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    public boolean authenticateUser(String username, String password) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    public Optional<User> findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
