package com.gradingSystem.servlets.backend;

import com.gradingSystem.modules.SuperAdmin;

public class DeleteMethods {

    public static void removeAdmin(String id)
    {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.removeAdminById(id);
    }
}
