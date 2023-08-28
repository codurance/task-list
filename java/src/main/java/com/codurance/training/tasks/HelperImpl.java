package com.codurance.training.tasks;

import java.io.PrintWriter;

public class HelperImpl implements  Helper{
    private  final PrintWriter out = new PrintWriter(System.out);
    @Override
    public void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }
}
