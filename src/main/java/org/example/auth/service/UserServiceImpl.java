package org.example.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.auth.dto.LoginRequest;
import org.example.auth.dto.LoginResponse;
import org.example.auth.model.User;
import org.example.auth.repository.UserRepository;
import org.example.auth.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;



    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info("Authenticated user: {}", authentication);
        if (authentication.isAuthenticated()) {
            String token = tokenService.generateToken(authentication);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setUsername(authentication.getName());
            loginResponse.setRole(authentication.getAuthorities().iterator().next().getAuthority());
            return loginResponse;
        }
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user).getUsername();
    }

}
