package com.stream.app.service.impl;

import com.stream.app.model.Login;
import com.stream.app.repository.LoginRepository;
import com.stream.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Login registerUser(String email, String username, String rawPassword) {
        Login login = new Login();
        login.setEmail(email);
        login.setUsername(username);
        login.setPassword(passwordEncoder.encode(rawPassword)); // Encripta la contraseña

        return loginRepository.save(login);
    }

    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword); // Valida la contraseña
    }

    @Override
    public Login findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }
}