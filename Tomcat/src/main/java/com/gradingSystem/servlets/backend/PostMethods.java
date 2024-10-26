package com.gradingSystem.servlets.backend;

import com.gradingSystem.modules.SuperAdmin;

public class PostMethods {

    public static void addAdmin(String name, String username, String password)

    {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.addAdmin(name,username, password);
    }
}
