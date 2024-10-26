package com.GradingSystemSpring.backend;

import com.GradingSystemSpring.modules.Course;
import com.GradingSystemSpring.modules.Enrollment;
import com.GradingSystemSpring.modules.Student;
import com.GradingSystemSpring.modules.Teacher;
import com.GradingSystemSpring.repositories.CourseRepository;
import com.GradingSystemSpring.repositories.EnrollmentRepository;
import com.GradingSystemSpring.repositories.StudentRepository;
import com.GradingSystemSpring.repositories.TeacherRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
public class UpdateMethods {

    @Autowired
    EnrollmentRepository enrollmentRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;

    public Map<String, Object> editGrade(int studentID, int courseID, BigDecimal newGrade) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("POST");
        try {
            // Find the enrollment record
            Enrollment enrollment = enrollmentRepository.findByCourseIdAndStudentId(courseID, studentID);

            if (enrollment != null) {
                // Update the grade
                enrollment.setGrade(newGrade);
                enrollmentRepository.save(enrollment);
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("error", "Enrollment record not found.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }

        return response;
    }
    public Map<String, Object> addStudentToCourse(int studentID, int courseID) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Check if the student is already enrolled in the course
            Enrollment existingEnrollment = enrollmentRepository.findByCourseIdAndStudentId(courseID, studentID);

            if (existingEnrollment != null) {
                response.put("success", false);
                response.put("error", "Student is already enrolled in this course.");
                return response;
            }

            // Find the student and the course by their IDs
            Student student = studentRepository.findById(studentID);
            Course course = courseRepository.findById(courseID).orElse(null);

            if (student != null && course != null) {
                // Create a new enrollment record
                Enrollment newEnrollment = new Enrollment();
                newEnrollment.setStudent(student);
                newEnrollment.setCourse(course);

                // Save the enrollment
                enrollmentRepository.save(newEnrollment);

                response.put("success", true);
                response.put("message", "Student added to course successfully!");
            } else {
                response.put("success", false);
                response.put("error", "Student or course not found.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to add student to course: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> assignTeacherToCourse(int teacherID, int courseID) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Check if the course already has a teacher assigned
            Course course = courseRepository.findById(courseID).orElse(null);
            if (course != null && course.getTeacher() != null) {
                response.put("success", false);
                response.put("error", "This course already has a teacher assigned.");
                return response;
            }

            // Find the teacher by their ID
            Teacher teacher = teacherRepository.findById(teacherID).orElse(null);
            if (teacher != null && course != null) {
                // Assign the teacher to the course
                course.setTeacher(teacher);

                // Save the updated course with the teacher assignment
                courseRepository.save(course);

                response.put("success", true);
                response.put("message", "Teacher assigned to course successfully!");
            } else {
                response.put("success", false);
                response.put("error", "Teacher or course not found.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to assign teacher to course: " + e.getMessage());
        }

        return response;
    }


}
