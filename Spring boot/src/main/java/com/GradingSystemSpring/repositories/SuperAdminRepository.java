package com.GradingSystemSpring.repositories;

import com.GradingSystemSpring.modules.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer> {
    SuperAdmin findByUsername(String username);
}
