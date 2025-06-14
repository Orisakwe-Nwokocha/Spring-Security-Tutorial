package org.example.auth.security;

import lombok.extern.slf4j.Slf4j;
import org.example.auth.model.Authority;
import org.example.auth.model.User;
import org.example.auth.repository.AuthorityRepository;
import org.example.auth.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public CustomUserDetailsService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user {}", username);
        String message = String.format("User %s not found", username);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(message));
        List<Authority> authorities = authorityRepository.findByUser(user);

        UserDetailsImpl userDetails = new UserDetailsImpl(user, authorities);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                .toList();
//        return userDetails;

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), simpleGrantedAuthorities);
    }
}
