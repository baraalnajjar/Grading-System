package com.GradingSystemSpring.controllers;

import com.GradingSystemSpring.backend.GetMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;


@Controller
public class LoginController {
    @Autowired
    private GetMethods getMethod;


    @GetMapping("/login")
    public ModelAndView showLoginPage(@RequestParam("role") String role) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    @PostMapping("/performLogin")
    @ResponseBody
    public Map<String, Object> performLogin(@RequestParam("role") String role,
                                            @RequestParam("username") String username,
                                            @RequestParam("password") String password) {

        String correctPassword = getMethod.getPassword(username, role);

        Map<String, Object> response = new HashMap<>();

        if (password.equals(correctPassword)) {
            response.put("redirect", "/" + role.toLowerCase()+ "?username=" + username+ "&role=" + role.toLowerCase());
        } else {
            response.put("error", "Incorrect username or password");
        }

        return response;
    }

}
