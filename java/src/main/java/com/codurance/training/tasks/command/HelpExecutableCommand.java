package com.codurance.training.tasks.command;

import com.codurance.training.tasks.io.Screen;

public class HelpExecutableCommand implements ExecutableCommand {

    private final Screen screen;

    public HelpExecutableCommand(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void execute(CommandLine commandLine) {
        screen.println("Commands:");
        screen.println("  show");
        screen.println("  add project <project name>");
        screen.println("  add task <project name> <task description>");
        screen.println("  check <task ID>");
        screen.println("  uncheck <task ID>");
        screen.println();
    }
}