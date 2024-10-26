package com.GradingSystemSpring.testService;

import com.GradingSystemSpring.modules.*;
import com.GradingSystemSpring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TestService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SuperAdminRepository superAdminRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository; // Add this for Student operations

    @Transactional(readOnly = true)
    public void printDatabaseDetails() {
        System.out.println("Admins:");
        List<Admin> admins = adminRepository.findAll();
        admins.forEach(admin -> System.out.println("Name: " + admin.getName()));

        System.out.println("\nSuper Admins:");
        List<SuperAdmin> superAdmins = superAdminRepository.findAll();
        superAdmins.forEach(superAdmin -> System.out.println("Name: " + superAdmin.getName()));

        System.out.println("\nTeachers:");
        List<Teacher> teachers = teacherRepository.findAll();
        teachers.forEach(teacher -> System.out.println("Name: " + teacher.getName()));

        System.out.println("\nStudents:");
        List<Student> students = studentRepository.findAll();
        students.forEach(student -> System.out.println("Name: " + student.getName()));

        System.out.println("\nCourses:");
        List<Course> courses = courseRepository.findAll();
        courses.forEach(course -> {
            System.out.println("Course Name: " + course.getName());
            System.out.println("Teacher ID: " + course.getTeacher().getId());
            System.out.println("Average: " + course.getAverage());
            System.out.println("Median: " + course.getMedian());
            System.out.println("Highest: " + course.getHighest());
            System.out.println("Lowest: " + course.getLowest());
            System.out.println();
        });

        System.out.println("Students and their grades:");
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        enrollments.forEach(enrollment -> {
            System.out.println("Student Name: " + enrollment.getStudent().getName());
            System.out.println("Course Name: " + enrollment.getCourse().getName());
            System.out.println("Grade: " + enrollment.getGrade());
            System.out.println();
        });
    }

    @Transactional
    public void addNewStudent(String name, String username, String password) {
        Student student = new Student();
        student.setName(name);
        student.setUsername(username);
        student.setPassword(password);

        studentRepository.save(student);

        System.out.println("Added new student: " + student.getName());
    }
    @Transactional
    public void removeStudent(int studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            System.out.println("Removed student with ID: " + studentId);
        } else {
            System.out.println("Student with ID " + studentId + " does not exist.");
        }
    }
}

