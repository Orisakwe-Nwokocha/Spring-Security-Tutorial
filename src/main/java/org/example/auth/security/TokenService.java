package org.example.auth.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.example.auth.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TokenService {

    @Value("${spring.application.name}")
    private String appName;


    public String generateToken(Authentication authentication) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret key");
            Instant now = Instant.now();
            String[] authorities = extractAuthorities(authentication.getAuthorities());
            return JWT.create()
                    .withSubject(appName)
                    .withIssuedAt(now)
                    .withExpiresAt(now.plus(1, ChronoUnit.HOURS))
                    .withClaim("user", authentication.getName())
                    .withClaim("isAdmin", false)
                    .withArrayClaim("roles", authorities)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("Error generating token", e);
            throw new RuntimeException(e);
        }
    }

    private static String[] extractAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {
        return grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toArray(String[]::new);
    }
}
