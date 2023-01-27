package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskList;
import java.io.PrintWriter;

public class DeadLineCommand implements Command {
    private final PrintWriter out;

    public DeadLineCommand(PrintWriter out) {
        this.out = out;
    }

    @Override
    public boolean appliesTo(String commandLine) {
        return commandLine.startsWith("deadline");
    }

    @Override
    public void execute(String commandLine, TaskList taskList) {
        System.err.println("OLE");
    }
}
