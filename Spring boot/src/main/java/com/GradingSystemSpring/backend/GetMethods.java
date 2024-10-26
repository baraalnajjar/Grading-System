package com.GradingSystemSpring.backend;
import com.GradingSystemSpring.modules.*;
import com.GradingSystemSpring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GetMethods {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SuperAdminRepository superAdminRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;


    public String getPassword(String username, String role) {
        switch (role.toLowerCase()) {
            case "admin":
                Admin admin = adminRepository.findByUsername(username);
                return admin != null ? admin.getPassword() : null;
            case "superadmin":
                SuperAdmin superAdmin = superAdminRepository.findByUsername(username);
                return superAdmin != null ? superAdmin.getPassword() : null;
            case "teacher":
                Teacher teacher = teacherRepository.findByUsername(username);
                return teacher != null ? teacher.getPassword() : null;
            case "student":
                Student student = studentRepository.findByUsername(username);
                return student != null ? student.getPassword() : null;
            default:
                return null;
        }
    }
    public int getID(String username, String role) {
        switch (role.toLowerCase()) {
            case "admin":
                Admin admin = adminRepository.findByUsername(username);
                return admin != null ? admin.getId() : null;
            case "superadmin":
                SuperAdmin superAdmin = superAdminRepository.findByUsername(username);
                return superAdmin != null ? superAdmin.getId() : null;
            case "teacher":
                Teacher teacher = teacherRepository.findByUsername(username);
                return teacher != null ? teacher.getId() : null;
            case "student":
                Student student = studentRepository.findByUsername(username);
                return student != null ? student.getId() : null;
            default:
                return Integer.parseInt(null);
        }
    }
    public List<Map<String, Object>> getStudentGrades(String username, String role) {

        return enrollmentRepository.findCoursesAndGradesForStudent(getID(username,role));
    }
    public List<Map<String, Object>> getCourses(String username) {
        // Fetch all courses from the repository
        Teacher teacher = teacherRepository.findByUsername(username);

        if (teacher == null) {
            // Handle case where teacher is not found
            return Collections.emptyList();
        }

        // Fetch courses associated with the teacher
        List<Course> courses = courseRepository.findByTeacher(teacher);

        // Transform courses to a list of maps with specific format
        return courses.stream().map(course -> {
            Map<String, Object> courseMap = new HashMap<>();
            courseMap.put("id", course.getId()); // Use the field name matching the HTML template
            courseMap.put("courseName", course.getName()); // Use the field name matching the HTML template
            courseMap.put("maxGrade", course.getHighest()); // Ensure this matches the HTML field names
            courseMap.put("medianGrade", course.getMedian());
            courseMap.put("averageGrade", course.getAverage());
            courseMap.put("lowestGrade", course.getLowest());
            return courseMap;
        }).collect(Collectors.toList());
    }
    public List<Map<String, Object>> getStudentsAndGradesForCourse(Integer courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);

        return enrollments.stream().map(enrollment -> {
            Map<String, Object> studentMap = new HashMap<>();
            Student student = enrollment.getStudent();

            studentMap.put("studentID", student.getId());
            studentMap.put("studentName", student.getName());
            studentMap.put("currentGrade", enrollment.getGrade());

            return studentMap;
        }).collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAdminsAndID() {
        List<Admin> admins = adminRepository.findAll();

        return admins.stream().map(admin -> {
            Map<String, Object> adminMap = new HashMap<>();
            adminMap.put("adminID", admin.getId());
            adminMap.put("adminName", admin.getUsername());
            return adminMap;
        }).collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> {
                    Map<String, Object> courseMap = Map.of(
                            "courseId", course.getId(),
                            "courseName", course.getName()
                    );
                    return courseMap;
                })
                .collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacher -> {
                    Map<String, Object> teacherMap = Map.of(
                            "teacherId", teacher.getId(),
                            "teacherName", teacher.getName()
                    );
                    return teacherMap;
                })
                .collect(Collectors.toList());
    }
    public List<Map<String, Object>> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> {
                    Map<String, Object> studentMap = Map.of(
                            "studentId", student.getId(),
                            "studentName", student.getName()
                    );
                    return studentMap;
                })
                .collect(Collectors.toList());
    }


}
