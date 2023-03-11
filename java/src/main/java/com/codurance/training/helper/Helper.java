package com.codurance.training.helper;

import java.io.PrintWriter;

public class Helper implements Helperinterface {
    public void help(PrintWriter out) {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  deadline <task ID> <date>");
        out.println("  today");
        out.println("  customize <task Id> <new Id>");
        out.println();
    }
}
