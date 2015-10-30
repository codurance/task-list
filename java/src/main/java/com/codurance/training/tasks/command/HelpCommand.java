package com.codurance.training.tasks.command;

import com.codurance.training.tasks.io.Screen;

public class HelpCommand extends Command {

    public HelpCommand(String name) {
        super(name);
    }

    public static void help(Screen screen) {
        screen.println("Commands:");
        screen.println("  show");
        screen.println("  add project <project name>");
        screen.println("  add task <project name> <task description>");
        screen.println("  check <task ID>");
        screen.println("  uncheck <task ID>");
        screen.println();
    }
}
