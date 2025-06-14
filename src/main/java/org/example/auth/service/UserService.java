package org.example.auth.service;

import org.example.auth.dto.LoginRequest;
import org.example.auth.dto.LoginResponse;
import org.example.auth.model.User;

import java.util.Optional;

public interface UserService {

    LoginResponse login(LoginRequest loginRequest);

    Optional<User> findByUsername(String username);
}
