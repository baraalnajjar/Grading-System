package com.GradingSystemSpring.repositories;

import com.GradingSystemSpring.modules.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByUsername(String username);
    Student findById(int id);

}

