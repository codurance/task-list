package com.codurance.training.tasks.command;

import com.codurance.training.tasks.io.Screen;

public class ErrorCommand {
    private static final String ERROR_COMMAND_NOT_FOUND = "I don't know what the command \"%s\" is.";

    public static void error(Screen screen, Command command) {
        screen.printf(ERROR_COMMAND_NOT_FOUND, command);
        screen.println();
    }
}
