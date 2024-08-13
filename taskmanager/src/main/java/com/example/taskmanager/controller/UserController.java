package com.example.taskmanager.controller;

import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/api/users/register")
    public String registerUser(@ModelAttribute User user) {
        if (user == null || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return "redirect:/register?error"; // Handle error case
        }
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/user/{username}")
    public String getUserByUsername(@PathVariable String username, Model model) {
        Optional<User> user = userService.getUserByUsername(username);
        model.addAttribute("user", user.orElse(null));
        return "user-details";
    }
}
