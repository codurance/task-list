package com.codurance.training.tasks;

import com.codurance.training.tasks.interfaces.TaskExceptions;

import java.io.PrintWriter;

public class TaskExceptionsImpl implements TaskExceptions {

    private final PrintWriter out;

    public TaskExceptionsImpl(PrintWriter out) {
        this.out = out;
    }

    public void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    public void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
