package com.GradingSystemSpring.backend;

import com.GradingSystemSpring.modules.Admin;
import com.GradingSystemSpring.modules.Course;
import com.GradingSystemSpring.modules.Student;
import com.GradingSystemSpring.modules.Teacher;
import com.GradingSystemSpring.repositories.AdminRepository;
import com.GradingSystemSpring.repositories.CourseRepository;
import com.GradingSystemSpring.repositories.StudentRepository;
import com.GradingSystemSpring.repositories.TeacherRepository;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;


@Service
public class PostMethods {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    CourseRepository courseRepository;

    public Map<String, Object> addAdmin(String name, String username, String password) {
        Map<String, Object> response = new HashMap<>();

        Admin newAdmin = new Admin();
        newAdmin.setName(name);
        newAdmin.setUsername(username);
        newAdmin.setPassword(password);

        try {
            adminRepository.save(newAdmin);

            response.put("success", true);
            response.put("message", "Admin added successfully!");
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to add admin: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> addStudent(String name, String username, String password) {
        Map<String, Object> response = new HashMap<>();

        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setUsername(username);
        newStudent.setPassword(password);

        try {
            // Save the student using the repository
            studentRepository.save(newStudent);

            response.put("success", true);
            response.put("message", "Student added successfully!");
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to add student: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> addTeacher(String name, String username, String password) {
        Map<String, Object> response = new HashMap<>();

        Teacher newTeacher = new Teacher();
        newTeacher.setName(name);
        newTeacher.setUsername(username);
        newTeacher.setPassword(password);

        try {
            // Save the teacher using the repository
            teacherRepository.save(newTeacher);

            response.put("success", true);
            response.put("message", "Teacher added successfully!");
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to add teacher: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> addCourse(String courseName) {
        Map<String, Object> response = new HashMap<>();

        Course newCourse = new Course();
        newCourse.setName(courseName);

        try {
            // Fetch the teacher by ID
            Teacher teacher = teacherRepository.findById(1).orElse(null); // Ensure you have this method in your repository

            if (teacher != null) {
                newCourse.setTeacher(teacher);
                courseRepository.save(newCourse);

                response.put("success", true);
                response.put("message", "Course added successfully and assigned to test Teacher 1!");
            } else {
                response.put("success", false);
                response.put("error", "Test Teacher not found");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to add course: " + e.getMessage());
        }

        return response;
    }

}
