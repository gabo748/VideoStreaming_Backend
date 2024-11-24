package com.stream.app.service;

import com.stream.app.model.Login;

public interface LoginService {
    public Login registerUser(String email, String username, String rawPassword);
    public boolean validatePassword(String rawPassword, String encodedPassword);

    public Login findByUsername(String username);
}
