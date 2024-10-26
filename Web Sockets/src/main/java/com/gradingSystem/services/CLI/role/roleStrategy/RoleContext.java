package com.gradingSystem.services.CLI.role.roleStrategy;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class RoleContext {
    private RoleStrategyInt roleStrategy;

    public RoleContext(RoleStrategyInt roleStrategy) {
        this.roleStrategy = roleStrategy;
    }

    public void executeStrategy(PrintWriter out, BufferedReader in) {
        roleStrategy.handleRole(out,in);
    }
}

