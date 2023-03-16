package com.codurance.training.services.ServicesImpl;

import com.codurance.training.services.Help;

import java.io.PrintWriter;

public class HelpCommandImpl implements Help {

    private PrintWriter out;

    public HelpCommandImpl(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <ID-Identifier> <task description> [Optional]<task deadline>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  today -> view tasks due for today");
        out.println("  delete <task id>");
        out.println("  view <[byDate|byDeadline|byProject]>");
        out.println();
    }
}
