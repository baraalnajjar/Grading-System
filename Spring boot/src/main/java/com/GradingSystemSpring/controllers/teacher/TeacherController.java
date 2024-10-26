package com.GradingSystemSpring.controllers.teacher;

import com.GradingSystemSpring.backend.GetMethods;
import com.GradingSystemSpring.backend.UpdateMethods;
import com.GradingSystemSpring.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    GetMethods getMethods;
    @Autowired
    UpdateMethods updateMethods;
    @Autowired
    EnrollmentRepository enrollmentRepository;


    @GetMapping()
    public String showTeacherPage(@RequestParam("username") String username, Model model) {

        List<Map<String, Object>> courseData = getMethods.getCourses(username);

        model.addAttribute("courses", courseData);
        model.addAttribute("username", username);

        return "teacher";
    }

    @GetMapping("/editGrade")
    public String editGradePage(@RequestParam("courseId") Integer courseId, Model model) {
        // Use the service to get students and their grades for the specific course
        List<Map<String, Object>> studentGrades = getMethods.getStudentsAndGradesForCourse(courseId);

        // Add attributes to the model for use in the view
        model.addAttribute("courseId", courseId);
        model.addAttribute("studentGrades", studentGrades);

        return "editGrade"; // This should point to your editGrade.html view
    }

    @PostMapping ("/editGrade")
    @ResponseBody
    public Map<String, Object> updateGrades(@RequestParam Map<String, String> params) {
        Map<String, Object> response;

        int courseID = Integer.parseInt(params.get("courseId"));
        BigDecimal newGradeStr = BigDecimal.valueOf(Integer.parseInt(params.get("newGrade_${student.studentID}")));
        int studentID = Integer.parseInt(params.get("studentID"));

        response=updateMethods.editGrade(studentID,courseID,newGradeStr);

        return response;
    }


}

