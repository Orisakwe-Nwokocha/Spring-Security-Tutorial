package org.example.auth.repository;

import org.example.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT count(u) > 0 FROM User u WHERE u.username=:username")
    boolean exists(String username);
}
