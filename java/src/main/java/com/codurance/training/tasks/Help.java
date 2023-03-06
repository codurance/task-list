package com.codurance.training.tasks;

import java.io.PrintWriter;

public class Help {
    public void help(PrintWriter out) {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description> <customisable task ID>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  delete  <task ID>");
        out.println("  deadline  <task ID> <Date of deadline(yyyy-mm-dd)>");
        out.println("  today");
        out.println("  view-by-deadline");
        out.println("  view-by-date");
        out.println("  view-by-project");
        out.println();
    }
}
