package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class TaskManager extends TaskImpl{

    private final BufferedReader in;
    private final PrintWriter out;
    public TaskManager(BufferedReader reader, PrintWriter writer){
        super();
        this.in = reader;
        this.out = writer;

    }
    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                super.show();
                break;
            case "add":
                super.add(commandRest[1]);
                break;
            case "check":
                super.check(commandRest[1]);
                break;
            case "uncheck":
                super.uncheck(commandRest[1]);
                break;
            case "delete":
                super.delete(commandRest[1]);
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

}
