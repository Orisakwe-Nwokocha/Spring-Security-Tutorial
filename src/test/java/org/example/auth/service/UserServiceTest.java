package org.example.auth.service;

import org.example.auth.dto.LoginRequest;
import org.example.auth.dto.LoginResponse;
import org.example.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
@Sql("/data.sql")
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("password");
        LoginResponse loginResponse = userService.login(loginRequest);

        assertNotNull(loginResponse);
        assertNotNull(loginResponse.getToken());
        String role = loginResponse.getRole();
        assertEquals("ADMIN", role);

        System.out.println(loginResponse);
    }

    @Test
    void existsTest() {

        boolean exists = userRepository.exists("admins");
        System.out.println("exists: " + exists);
        assertTrue(exists);
        System.out.println(userRepository.findAll());
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("password"));
    }
}