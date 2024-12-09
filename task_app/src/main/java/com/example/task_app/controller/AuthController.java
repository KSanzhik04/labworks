package com.example.task_app.controller;
import com.example.task_app.model.User;
import com.example.task_app.repository.UserRepository;
import com.example.task_app.security.SecurityConfig;

import java.util.HashMap;
import java.util.Map;

public class AuthController {

    private final UserRepository userRepository = new UserRepository();
    private final SecurityConfig securityConfig = new SecurityConfig(userRepository);

    // Simulates a session state for simplicity
    private final Map<String, Boolean> loggedInUsers = new HashMap<>();

    public String register(String username, String password, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "Username already exists!";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole("USER");

        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        if (securityConfig.authenticate(username, password)) {
            loggedInUsers.put(username, true);
            return "Logged in successfully";
        }

        return "Invalid username or password";
    }

    public String logout(String username) {
        if (securityConfig.isLoggedIn(username)) {
            securityConfig.logout(username);
            loggedInUsers.remove(username);
            return "Logged out successfully";
        }

        return "No active session";
    }

    public boolean isUserLoggedIn(String username) {
        return loggedInUsers.containsKey(username);
    }
}
