package com.GradingSystemSpring.controllers.admin;

import com.GradingSystemSpring.backend.DeleteMethods;
import com.GradingSystemSpring.backend.GetMethods;
import com.GradingSystemSpring.backend.PostMethods;
import com.GradingSystemSpring.backend.UpdateMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PostMethods postMethods;
    @Autowired
    GetMethods getMethods;
    @Autowired
    DeleteMethods deleteMethods;
    @Autowired
    UpdateMethods updateMethods;

    @GetMapping()
    public String adminDashboard(Model model) {
        return "admin/admin";
    }

    @GetMapping("/manageStudents")
    public String manageStudents(Model model) {
        List<Map<String, Object>> students = getMethods.getAllStudents();
        model.addAttribute("StudentAndID", students);
        return "admin/manageStudents";
    }


    @GetMapping("/manageTeachers")
    public String manageTeachers(Model model) {
        List<Map<String, Object>> teachers = getMethods.getAllTeachers();
        model.addAttribute("TeacherAndID", teachers);
        return "admin/manageTeachers";
    }

    @GetMapping("/manageCourses")
    public String manageCourses(Model model) {
        List<Map<String, Object>> courses = getMethods.getAllCourses();
        model.addAttribute("CourseAndID", courses);
        return "admin/manageCourses";
    }

    @GetMapping("/manageStudents/addStudent")
    public String addStudent() {

        return "admin/addStudent";
    }

    @GetMapping("/manageStudents/removeStudent")
    public String removeStudent() {

        return "admin/removeStudent";
    }

    @GetMapping("/manageStudents/enrollStudent")
    public String enrollStudent() {

        return "admin/enrollStudent";
    }

    @GetMapping("/manageTeachers/addTeacher")
    public String addTeacher() {

        return "admin/addTeacher";
    }
    @GetMapping("/manageTeachers/removeTeacher")
    public String removeTeacher() {

        return "admin/removeTeacher";
    }
    @GetMapping("/manageTeachers/AssignToCourse")
    public String AssignToCourse() {

        return "admin/AssignToCourse";
    }
    @GetMapping("/manageCourses/addCourse")
    public String addCourse() {

        return "admin/addCourse";
    }
    @GetMapping("/manageCourses/removeCourse")
    public String removeCourse() {

        return "admin/removeCourse";
    }
    @GetMapping("/manageCourses/assignCourse")
    public String AssignToTeacher() {

        return "admin/AssignToTeacher";
    }


    @PostMapping("/manageStudents/addStudent")
    @ResponseBody
    public Map<String, Object> handleAddStudent(
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        return postMethods.addStudent(name, username, password);

    }

    @PostMapping("/manageStudents/removeStudent")
    @ResponseBody
    public Map<String, Object> handleRemoveStudent(@RequestParam("studentId") int id) {

        return deleteMethods.deleteStudent(id);

    }

    @PostMapping("/manageStudents/enrollStudent")
    @ResponseBody
    public Map<String, Object> handleenrollStudent(
            @RequestParam("studentID") int studentID,
            @RequestParam("courseID") int courseID) {
        return updateMethods.addStudentToCourse(studentID, courseID);

    }

    @PostMapping("/manageStudents/addTeacher")
    @ResponseBody
    public Map<String, Object> handleAddTeacher(
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        return postMethods.addTeacher(name, username, password);

    }

    @DeleteMapping("/manageTeachers/removeTeacher")
    @ResponseBody
    public Map<String, Object> handleRemoveTeacher(@RequestParam("teacherId") int id) {

        return deleteMethods.deleteTeacher(id);

    }

    @PostMapping("/manageTeachers/AssignToCourse")
    @ResponseBody
    public Map<String, Object> handleAssignTeacher(
            @RequestParam("teacherID") int teacherID,
            @RequestParam("courseID") int courseID) {
        return updateMethods.assignTeacherToCourse(teacherID, courseID);
    }


    @PostMapping("/manageCourses/addCourse")
    @ResponseBody
    public Map<String, Object> handleAddCourse(
            @RequestParam("courseName") String courseName) {

        return postMethods.addCourse(courseName);
    }

    @DeleteMapping("/manageCourses/removeCourse")
    @ResponseBody
    public Map<String, Object> handleRemoveCourse(@RequestParam("courseId") int id) {

        return deleteMethods.deleteCourse(id);

    }

    @PostMapping("/manageCourses/assignCourse")
    @ResponseBody
    public Map<String, Object> handleAssignCoruse(
            @RequestParam("teacherID") int teacherID,
            @RequestParam("courseID") int courseID) {
        return updateMethods.assignTeacherToCourse(teacherID, courseID);
    }
}
