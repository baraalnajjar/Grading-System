package com.GradingSystemSpring.repositories;

import com.GradingSystemSpring.modules.Course;
import com.GradingSystemSpring.modules.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByTeacher(Teacher teacher);

}

