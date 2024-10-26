package com.GradingSystemSpring.controllers.student;

import com.GradingSystemSpring.backend.GetMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    private GetMethods getMethod;


    @GetMapping("/student")
    public String showStudentPage(@RequestParam("username") String username,@RequestParam("role") String role, Model model) {
        List<Map<String, Object>> courses = getMethod.getStudentGrades(username,role);
        model.addAttribute("courses", courses);
        return "student";
    }
}

