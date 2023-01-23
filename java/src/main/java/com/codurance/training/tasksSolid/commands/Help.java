package com.codurance.training.tasksSolid.commands;

import java.io.PrintWriter;

public class Help {

    private final PrintWriter out;

    public Help(PrintWriter out) {
        this.out = out;
    }

    public void printAvailableCommands() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }
}
