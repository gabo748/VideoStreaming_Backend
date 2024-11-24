package com.stream.app.controller;

import com.stream.app.model.Login;
import com.stream.app.payload.CustomMessage;
import com.stream.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> registerUser(@RequestParam String email, @RequestParam String username, @RequestParam String password) {
        loginService.registerUser(email, username, password);


        CustomMessage successResponse = new CustomMessage("User registered successfully!", true);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(successResponse);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestParam String username, @RequestParam String password) {
        Login login = loginService.findByUsername(username);
        if (login != null && loginService.validatePassword(password, login.getPassword())) {
            CustomMessage successResponse = new CustomMessage("User Login in successfully!", true);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(successResponse);
        }

        CustomMessage failureResponse = new CustomMessage("Credentials Invalid", false);
        return ResponseEntity.status(401).body(failureResponse);
    }
}