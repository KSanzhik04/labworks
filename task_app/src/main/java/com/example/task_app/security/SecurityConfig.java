package com.example.task_app.security;
import com.example.task_app.model.User;
import com.example.task_app.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SecurityConfig {

    private final UserService userService;
    private final Map<String, User> activeSessions;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
        this.activeSessions = new HashMap<>();
    }

    public boolean authenticate(String username, String password) {
        Optional<User> userOpt = userService.findUserByUsername(username);
        return userOpt.isPresent() && userOpt.get().getPassword().equals(password);
    }

    public User login(String username, String password) {
        if (authenticate(username, password)) {
            User user = userService.findUserByUsername(username).get();
            activeSessions.put(username, user); // Simulate session storage.
            return user;
        }
        return null;
    }

    public void logout(String username) {
        activeSessions.remove(username);
    }

    public boolean isLoggedIn(String username) {
        return activeSessions.containsKey(username);
    }

    public boolean isAdmin(String username) {
        if (isLoggedIn(username)) {
            return activeSessions.get(username).getRole().equalsIgnoreCase("ADMIN");
        }
        return false;
    }

    public Optional<User> getLoggedInUser(String username) {
        return Optional.ofNullable(activeSessions.get(username));
    }
}

