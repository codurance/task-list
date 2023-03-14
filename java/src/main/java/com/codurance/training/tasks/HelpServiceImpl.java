package com.codurance.training.tasks;

import java.io.PrintWriter;

public class HelpServiceImpl implements HelpService{
    @Override
    public void userHelp(PrintWriter out) {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }
}
