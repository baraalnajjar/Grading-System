package com.GradingSystemSpring.backend;
import com.GradingSystemSpring.repositories.AdminRepository;
import com.GradingSystemSpring.repositories.CourseRepository;
import com.GradingSystemSpring.repositories.StudentRepository;
import com.GradingSystemSpring.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeleteMethods {


    @Autowired
    AdminRepository adminRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    CourseRepository courseRepository;

    public Map<String, Object> deleteAdmin(int adminId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Check if the admin exists
            if (adminRepository.existsById(adminId)) {
                // Delete the admin record
                adminRepository.deleteById(adminId);
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("error", "Admin not found.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }

        return response;
    }
    public Map<String, Object> deleteStudent(int studentId) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Check if the student exists
            if (studentRepository.existsById(studentId)) {
                // Delete the student record
                studentRepository.deleteById(studentId);
                response.put("success", true);
                response.put("message", "Student deleted successfully.");
            } else {
                response.put("success", false);
                response.put("error", "Student not found.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }

        return response;
    }
    public Map<String, Object> deleteTeacher(int teacherID) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Check if the student exists
            if (teacherRepository.existsById(teacherID)) {
                // Delete the student record
                teacherRepository.deleteById(teacherID);
                response.put("success", true);
                response.put("message", "Teacher deleted successfully.");
            } else {
                response.put("success", false);
                response.put("error", "Teacher not found.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }

        return response;
    }
    public Map<String, Object> deleteCourse(int courseID) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Check if the student exists
            if (courseRepository.existsById(courseID)) {
                // Delete the student record
                courseRepository.deleteById(courseID);
                response.put("success", true);
                response.put("message", "Course deleted successfully.");
            } else {
                response.put("success", false);
                response.put("error", "Course not found.");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
        }

        return response;
    }
}
