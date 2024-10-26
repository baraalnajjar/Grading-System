package com.gradingSystem.services.CLI.role.roleStrategy.strategyImplementaion;

import com.gradingSystem.modules.Student;
import com.gradingSystem.services.CLI.role.roleStrategy.RoleStrategyInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class StudentStrategyImpl implements RoleStrategyInt {

    @Override
    public void handleRole(PrintWriter out, BufferedReader in) {

        try {
            out.println("Enter your username:");
            String username = in.readLine();
            System.out.println("Received from client: " + username);
            Student student= new Student(username);

            out.println("Enter your password:");
            String password = in.readLine();

            if(student.verifyPassword(password))
            {
                out.println("Welcome " + student.getName() + "! Here are your grades:");
                out.println(student.getCoursesAndGrades());
                out.println("Good Luck");

            }
            else {
                out.println("invalid user name or password");

            }


        } catch (IOException e) {
            e.printStackTrace();
            out.println("An error occurred while processing your input. Please try again.");
        }
    }

}

