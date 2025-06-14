package org.example.auth.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authority")
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    private User user;
}
