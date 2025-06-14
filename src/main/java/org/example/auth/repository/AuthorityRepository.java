package org.example.auth.repository;

import org.example.auth.model.Authority;
import org.example.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findByUser(User user);
}
