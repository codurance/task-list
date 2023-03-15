package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.service.IHelpService;

import java.io.PrintWriter;

public class HelpServiceImpl implements IHelpService {
    private PrintWriter out;

    public HelpServiceImpl(PrintWriter out) {
        this.out = out;
    }

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
