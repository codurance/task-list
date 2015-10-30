package com.codurance.training.tasks.command;

import com.codurance.training.tasks.io.Screen;

public class ErrorCommand implements Command {
    private static final String ERROR_COMMAND_NOT_FOUND = "I don't know what the command \"%s\" is.";
    private Screen screen;

    public ErrorCommand(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void execute(CommandLine commandLine) {
        Command command = commandLine.getCommand();
        screen.printf(ERROR_COMMAND_NOT_FOUND, command);
        screen.println();
    }
}
