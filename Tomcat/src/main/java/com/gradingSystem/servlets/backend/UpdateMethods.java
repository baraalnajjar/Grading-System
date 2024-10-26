package com.gradingSystem.servlets.backend;

import com.gradingSystem.modules.Teacher;


public class UpdateMethods {

    public static void updateStudentGrade(String studentId, String courseId, double newGrade)
    {
        Teacher teacher = new Teacher();
        teacher.updateStudentGrade(studentId,courseId,newGrade);
        System.out.println("Grade Updated Successfully");
    }
}
