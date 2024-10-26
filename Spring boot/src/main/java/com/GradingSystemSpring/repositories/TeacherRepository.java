package com.GradingSystemSpring.repositories;

import com.GradingSystemSpring.modules.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByUsername(String username);
}
