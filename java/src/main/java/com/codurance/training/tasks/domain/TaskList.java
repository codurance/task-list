package com.codurance.training.tasks.domain;

import com.codurance.training.tasks.command.*;
import com.codurance.training.tasks.io.Keyboard;
import com.codurance.training.tasks.io.Screen;

import java.io.IOException;

import static com.codurance.training.tasks.command.Command.*;

public final class TaskList implements Runnable {
    private static final String COMMAND_PROMPT = "> ";

    private static final boolean KEEP_RUNNING = true;

    private final Keyboard keyboard;
    private final Screen screen;
    private final ProjectsToTasks projectsToTasks;

    public static void main(String[] args) throws Exception {
        Keyboard keyboard = new Keyboard();
        Screen screen = new Screen();
        new TaskList(keyboard, screen).run();
    }

    public TaskList(Keyboard keyboard, Screen screen) {
        this.keyboard = keyboard;
        this.screen = screen;
        projectsToTasks = new ProjectsToTasks(screen);
    }

    public void run() {
        while (KEEP_RUNNING) {
            screen.print(COMMAND_PROMPT);

            CommandLine commandLine;
            try {
                commandLine = new CommandLine(keyboard.readLine(), projectsToTasks);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (commandLine.contains(CMD_QUIT)) {
                break;
            }

            execute(commandLine);
        }
    }

    private void execute(CommandLine commandLine) {
        Command command = commandLine.getCommand();

        if (command instanceof TaskListShowCommand) command.execute();
        else if (command instanceof TaskListAddCommand) ((TaskListAddCommand) command).add(commandLine);
        else if (command instanceof TaskListCheckCommand) ((TaskListCheckCommand)command).check(commandLine);
        else if (command instanceof TaskListUnCheckCommand) ((TaskListUnCheckCommand)command).unCheck(commandLine);
        else if (command instanceof QuitCommand) return;
        else if (command instanceof HelpCommand) HelpCommand.help(screen);
        else ErrorCommand.error(screen, command);
    }
}