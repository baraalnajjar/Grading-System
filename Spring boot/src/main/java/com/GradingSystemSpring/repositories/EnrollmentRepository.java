package com.GradingSystemSpring.repositories;

import com.GradingSystemSpring.modules.Enrollment;
import com.GradingSystemSpring.modules.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;


public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    @Query("SELECT new map(c.name AS courseName, e.grade AS grade) " +
            "FROM Enrollment e " +
            "JOIN e.course c " +
            "WHERE e.student.id = :studentId")
    List<Map<String, Object>> findCoursesAndGradesForStudent(@Param("studentId") Integer studentId);

    List<Enrollment> findByCourseId(Integer courseId);

    Enrollment findByCourseIdAndStudentId(Integer courseId, Integer studentId);
}
