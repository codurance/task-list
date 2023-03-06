package com.codurance.training.tasks;
import java.io.PrintWriter;

public class HelperServiceImpl implements HelperService {

    private final PrintWriter out;
    public HelperServiceImpl(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void help() {
        out.println("  show");
        out.println("  add project <project name>");
        out.println("Commands:");
        out.println("  add task <project name> <task description>");
        out.println("  add task <project name> <task description>");
        out.println("  deadline  <task ID> <Date(dd/MM/yyyy)>");
        out.println("  delete  <task ID>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  viewbydeadline");
        out.println("  viewbydate");
        out.println("  viewbyproject");
        out.println();
    }
}
