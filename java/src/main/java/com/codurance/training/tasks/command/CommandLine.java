package com.codurance.training.tasks.command;

import com.codurance.training.tasks.command.executable.TaskListAddTaskExecutableCommand;
import com.codurance.training.tasks.command.executable.*;
import com.codurance.training.tasks.command.nonexecutable.NonExecutableCommand;
import com.codurance.training.tasks.command.nonexecutable.QuitCommand;
import com.codurance.training.tasks.command.nonexecutable.UnknownCommand;
import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.io.Screen;

public class CommandLine {

    private static final String COMMAND_SEPARATOR = " ";
    private static final int TIMES_TO_APPLY_SEPARATOR = 2;

    private static final int COMMAND = 0;
    private static final int COMMAND_FIRST_PARAMETER = 1;
    private static final int REST_OF_THE_PARAMETERS = 1;

    private static final int SUB_COMMAND_FIRST_PARAMETER = 0;

    private static final String NOTHING = "";

    private final String commandLineInput;
    private ProjectsToTasks projectsToTasks;
    private Screen screen;

    public CommandLine(String commandLineInput, Screen screen, ProjectsToTasks projectsToTasks) {
        this.commandLineInput = commandLineInput;
        this.screen = screen;
        this.projectsToTasks = projectsToTasks;
    }

    public NonExecutableCommand getCommand() {
        String[] commandLineSplit = parseCommandLine();

        String commandName = commandLineSplit[COMMAND];
        switch (commandName) {
            case ExecutableCommand.CMD_SHOW:
                return new TaskListShowExecutableCommand(projectsToTasks);
            case ExecutableCommand.CMD_ADD:
                return new TaskListAddExecutableCommand(screen, projectsToTasks);
            case ExecutableCommand.SUB_CMD_PROJECT:
                return new TaskListAddProjectExecutableCommand(projectsToTasks);
            case ExecutableCommand.SUB_CMD_TASK:
                return new TaskListAddTaskExecutableCommand(screen, projectsToTasks);
            case ExecutableCommand.CMD_UNCHECK:
                return new TaskListUnCheckExecutableCommand(projectsToTasks);
            case ExecutableCommand.CMD_CHECK:
                return new TaskListCheckExecutableCommand(projectsToTasks);
            case ExecutableCommand.CMD_HELP:
                return new HelpExecutableCommand(screen);
            case ExecutableCommand.CMD_QUIT:
                return new QuitCommand();
        }

        return new UnknownCommand(commandName);
    }

    public String getFirstParameter() {
        String parameters[] = parseCommandLineFor(COMMAND_FIRST_PARAMETER).split(COMMAND_SEPARATOR);
        return parameters[SUB_COMMAND_FIRST_PARAMETER];
    }

    private String parseCommandLineFor(int index) {
        String[] commandLineSplit = parseCommandLine();
        return commandLineSplit[index];
    }

    public String getRestOfParameters() {
        String[] commandLineSplit = parseCommandLine();
        if (commandLineSplit.length > 1) return commandLineSplit[REST_OF_THE_PARAMETERS];
        return NOTHING;
    }

    private String[] parseCommandLine() {
        return commandLineInput.split(COMMAND_SEPARATOR, TIMES_TO_APPLY_SEPARATOR);
    }

    public boolean contains(String command) {
        return commandLineInput.equals(command);
    }
}