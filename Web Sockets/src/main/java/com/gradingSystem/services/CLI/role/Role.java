package com.gradingSystem.services.CLI.role;

import com.gradingSystem.services.CLI.role.roleStrategy.*;
import com.gradingSystem.services.CLI.role.roleStrategy.strategyImplementaion.*;

public enum Role {
    STUDENT("Student", new StudentStrategyImpl()),
    TEACHER("Teacher", new TeacherStrategyImpl()),
    ADMIN("Admin", new AdminStrategyImpl()),
    SUPER_ADMIN("Super Admin", new SuperAdminStrategyImpl());

    private final String displayName;
    private final RoleStrategyInt strategy;

    Role(String displayName, RoleStrategyInt strategy) {
        this.displayName = displayName;
        this.strategy = strategy;
    }

    public String getDisplayName() {
        return displayName;
    }

    public RoleStrategyInt getStrategy() {
        return strategy;
    }

    public static String getPrompt() {
        StringBuilder sb = new StringBuilder();
        int choices = 0;
        for (int i = 0; i < Role.values().length; i++) {
            sb.append(i + 1).append(". ").append(Role.values()[i].getDisplayName()).append("\n");
            choices=i+1;
        }
        sb.append("Enter your choice (1-"+choices+")");
        return sb.toString();
    }
}
