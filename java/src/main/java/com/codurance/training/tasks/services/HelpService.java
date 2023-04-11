package com.codurance.training.tasks.services;

import com.codurance.training.tasks.interfaces.IHelpService;

import java.io.PrintWriter;

public class HelpService implements IHelpService {
    private PrintWriter out;

    public HelpService(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void getHelp() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <ID-Identifier> <task description> [Optional]<task deadline>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  today -> to view today's due tasks");
        out.println("  delete <task id>");
        out.println("  view <[byDate|byDeadline|byProject]>");
        out.println();
    }
}
