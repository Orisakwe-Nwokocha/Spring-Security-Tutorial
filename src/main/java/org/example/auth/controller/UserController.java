package org.example.auth.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.auth.dto.LoginRequest;
import org.example.auth.dto.LoginResponse;
import org.example.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/free")
    public ResponseEntity<?> freeEndpoint() {
        log.info("Request to free endpoint");
        return ResponseEntity.status(HttpStatus.CREATED).body("Hello World");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Request to login endpoint: {}", loginRequest);
        LoginResponse login = userService.login(loginRequest);
        return ResponseEntity.ok(login);
    }

    @GetMapping("/locked")
    public ResponseEntity<?> lockedEndpoint(Principal principal) {
        String name = principal.getName();
        log.info("Request to locked endpoint: {}", name);
        return ResponseEntity.ok("Hello " + name);
    }

}
