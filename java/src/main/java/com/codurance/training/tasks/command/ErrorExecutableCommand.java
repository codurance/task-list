package com.codurance.training.tasks.command;

import com.codurance.training.tasks.io.Screen;

public class ErrorExecutableCommand implements ExecutableCommand {
    private static final String ERROR_COMMAND_NOT_FOUND = "I don't know what the command \"%s\" is.";
    private Screen screen;

    public ErrorExecutableCommand(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void execute(CommandLine commandLine) {
        NonExecutableCommand command = commandLine.getCommand();
        screen.printf(ERROR_COMMAND_NOT_FOUND, command);
        screen.println();
    }
}