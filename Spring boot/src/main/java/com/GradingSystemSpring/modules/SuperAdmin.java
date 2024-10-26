package com.GradingSystemSpring.modules;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
