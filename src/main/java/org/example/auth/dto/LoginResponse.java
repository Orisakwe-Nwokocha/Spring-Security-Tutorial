package org.example.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginResponse {


    private String username;

    private String password;

    private String token;

    private String role;

}
