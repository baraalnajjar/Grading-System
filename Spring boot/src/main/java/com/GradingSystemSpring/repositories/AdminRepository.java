package com.GradingSystemSpring.repositories;

import com.GradingSystemSpring.modules.Admin;
import com.GradingSystemSpring.modules.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
}
