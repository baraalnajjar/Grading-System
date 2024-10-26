package com.GradingSystemSpring.controllers.superAdmin;

import com.GradingSystemSpring.backend.DeleteMethods;
import com.GradingSystemSpring.backend.GetMethods;
import com.GradingSystemSpring.backend.PostMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {

    @Autowired
    GetMethods getMethods;
    @Autowired
    PostMethods postMethods;
    @Autowired
    DeleteMethods deleteMethods;

    @GetMapping()
    public String viewAdmins(Model model) {
        List<Map<String, Object>> AdminsAndID = getMethods.getAdminsAndID();
        model.addAttribute("AdminsAndID", AdminsAndID);
        return "viewAdmins";
    }
    @GetMapping("/addAdmin")
    public String addAdmin() {
        return "addAdmin";
    }
    @GetMapping("/removeAdmin")
    public String removeAdmin() {
        return "removeAdmin";
    }



    @PostMapping("/addAdmin")
    @ResponseBody
    public Map<String, Object> addAdmin(@RequestParam("adminName") String name,
                                        @RequestParam("adminUsername") String username,
                                        @RequestParam("adminPassword") String password) {

        Map<String, Object> response = postMethods.addAdmin(name, username, password);

        return response;
    }


    @DeleteMapping("/removeAdmin")
    @ResponseBody
    public Map<String, Object> removeAdmin(@RequestParam("adminId") String adminId) {

        Map<String, Object> response = deleteMethods.deleteAdmin(Integer.parseInt(adminId));

        return response;

    }

}
