package com.codurance.training.tasks.command;

import com.codurance.training.tasks.io.Screen;

public class HelpCommand implements Command {

    private final Screen screen;

    public HelpCommand(Screen screen) {
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
