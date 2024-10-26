package com.GradingSystemSpring.modules;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;  // A course has one teacher

    private BigDecimal average;
    private BigDecimal median;
    private BigDecimal highest;
    private BigDecimal lowest;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;  // A course can have many students
}
