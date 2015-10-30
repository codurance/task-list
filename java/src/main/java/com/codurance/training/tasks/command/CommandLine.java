package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskListAddTaskCommand;
import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.io.Screen;

public class CommandLine {

    private static final String COMMAND_SEPARATOR = " ";
    private static final int TIMES_TO_APPLY_SEPARATOR = 2;

    private static final int COMMAND = 0;
    private static final int COMMAND_FIRST_PARAMETER = 1;
    private static final int REST_OF_THE_PARAMETERS = 1;

    private static final int SUBCOMMAND_FIRST_PARAMETER = 0;

    private static final String NOTHING = "";

    private final String commandLineInput;
    private ProjectsToTasks projectsToTasks;
    private Screen screen;

    public CommandLine(String commandLineInput, Screen screen, ProjectsToTasks projectsToTasks) {
        this.commandLineInput = commandLineInput;
        this.screen = screen;
        this.projectsToTasks = projectsToTasks;
    }

    public Command getCommand() {
        String[] commandLineSplit = parseCommandLine();

        String commandName = commandLineSplit[COMMAND];
        switch (commandName) {
            case Command.CMD_SHOW:
                return new TaskListShowCommand(projectsToTasks);
            case Command.CMD_ADD:
                return new TaskListAddCommand(screen, projectsToTasks);
            case Command.SUB_CMD_PROJECT:
                return new TaskListAddProjectCommand(projectsToTasks);
            case Command.SUB_CMD_TASK:
                return new TaskListAddTaskCommand(screen, projectsToTasks);
            case Command.CMD_UNCHECK:
                return new TaskListUnCheckCommand(projectsToTasks);
            case Command.CMD_CHECK:
                return new TaskListCheckCommand(projectsToTasks);
            case Command.CMD_HELP:
                return new HelpCommand(screen);
            case Command.CMD_QUIT:
                return new QuitCommand();

        }
        return new UnknownCommand(commandName);
    }

    public String getFirstParameter() {
        String parameters[] = parseCommandLineFor(COMMAND_FIRST_PARAMETER).split(COMMAND_SEPARATOR);
        return parameters[SUBCOMMAND_FIRST_PARAMETER];
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