package com.stream.app.controller;

import com.stream.app.model.Login;
import com.stream.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        loginService.registerUser(email, username, password);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateUser(@RequestParam String username, @RequestParam String password) {
        Login login = loginService.findByUsername(username);
        if (login != null && loginService.validatePassword(password, login.getPassword())) {
            return ResponseEntity.ok("Password is valid!");
        }
        return ResponseEntity.status(401).body("Invalid username or password!");
    }
}