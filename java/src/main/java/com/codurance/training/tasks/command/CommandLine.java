package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskListAddTaskCommand;
import com.codurance.training.tasks.domain.ProjectsToTasks;

public class CommandLine {

    private static final String COMMAND_SEPARATOR = " ";
    private static final int TIMES_TO_APPLY_SEPARATOR = 2;

    private static final int COMMAND = 0;
    private static final int FIRST_PARAMETER = 1;
    private static final int REST_OF_THE_PARAMETERS = 1;

    private static final String NOTHING = "";

    private final String commandLineInput;
    private ProjectsToTasks projectsToTasks;

    public CommandLine(String commandLineInput, ProjectsToTasks projectsToTasks) {
        this.commandLineInput = commandLineInput;
        this.projectsToTasks = projectsToTasks;
    }

    public Command getCommand() {
        String[] commandLineSplit = parseCommandLine();

        String commandName = commandLineSplit[COMMAND];
        switch (commandName) {
            case Command.CMD_SHOW:
                return new TaskListShowCommand(commandName, projectsToTasks);
            case Command.CMD_ADD:
                return new TaskListAddCommand(commandName, projectsToTasks);
            case Command.SUB_CMD_PROJECT:
                return new TaskListAddProjectCommand(commandName, projectsToTasks);
            case Command.SUB_CMD_TASK:
                return new TaskListAddTaskCommand(commandName, projectsToTasks);
            case Command.CMD_UNCHECK:
                return new TaskListUnCheckCommand(commandName, projectsToTasks);
            case Command.CMD_CHECK:
                return new TaskListCheckCommand(commandName, projectsToTasks);
            case Command.CMD_HELP:
                return new HelpCommand(commandName);
            case Command.CMD_QUIT:
                return new QuitCommand(commandName);

        }
        return new Command(commandName);
    }

    public String getFirstParameter() {
        String parameters[] = parseCommandLineFor(FIRST_PARAMETER).split(COMMAND_SEPARATOR);
        return parameters[0];
    }
    
    public String getSecondParameter() {
        String parameters[] = parseCommandLineFor(FIRST_PARAMETER).split(COMMAND_SEPARATOR);
        return parameters[1];
    }

    private String parseCommandLineFor(int index) {
        String[] commandLineSplit = parseCommandLine();
        return commandLineSplit[index];
    }

    private String[] parseCommandLine() {
        return commandLineInput.split(COMMAND_SEPARATOR, TIMES_TO_APPLY_SEPARATOR);
    }

    public String getRestOfParameters() {
        String[] commandLineSplit = parseCommandLine();

        if (commandLineSplit.length > 1) return commandLineSplit[REST_OF_THE_PARAMETERS];

        return NOTHING;
    }

    public boolean contains(String command) {
        return commandLineInput.equals(command);
    }

    @Override
    public String toString() {
        return commandLineInput;
    }
}