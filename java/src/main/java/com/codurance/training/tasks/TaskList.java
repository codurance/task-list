package com.codurance.training.tasks;

import java.io.IOException;

import static com.codurance.training.tasks.Command.*;
import static com.codurance.training.tasks.Task.NOT_DONE;

public final class TaskList implements Runnable {
    private static final String COMMAND_SEPARATOR = " ";

    private static final String COMMAND_PROMPT = "> ";

    private static final String SUB_CMD_PROJECT = "project";
    private static final String SUB_CMD_TASK = "task";

    private static final String ERROR_COMMAND_NOT_FOUND = "I don't know what the command \"%s\" is.";

    private static final boolean DONE = true;
    private static final boolean KEEP_RUNNING = true;

    private static final int TIMES_TO_APPLY_SEPARATOR = 2;

    private static final int FIRST_PART_OF_PARAM = 0;
    private static final int REST_OF_PARAMS = 1;

    private final Keyboard keyboard;
    private final Screen screen;
    private final ProjectsToTasks projectsToTasks;
    private int lastId;

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
                commandLine = new CommandLine(keyboard.readLine());
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

        if (command.equals(CMD_SHOW)) projectsToTasks.show();
        else if (command.equals(CMD_ADD)) add(commandLine.getFirstParameter());
        else if (command.equals(CMD_CHECK)) check(commandLine.getFirstParameter());
        else if (command.equals(CMD_UNCHECK)) unCheck(commandLine.getFirstParameter());
        else if (command.equals(CMD_QUIT)) ;
        else if (command.equals(CMD_HELP)) help();
        else error(command);
    }

    private void add(String commandLine) {
        String[] subCommandRest = commandLine.split(COMMAND_SEPARATOR, TIMES_TO_APPLY_SEPARATOR);
        String subCommand = subCommandRest[FIRST_PART_OF_PARAM];
        if (subCommand.equals(SUB_CMD_PROJECT)) {
            addProject(subCommandRest[REST_OF_PARAMS]);
        } else if (subCommand.equals(SUB_CMD_TASK)) {
            addTaskToProject(subCommandRest[REST_OF_PARAMS]);
        }
    }

    private void addTaskToProject(String restOfCommandLine) {
        String[] commandParameters = restOfCommandLine.split(COMMAND_SEPARATOR, TIMES_TO_APPLY_SEPARATOR);

        String projectName = commandParameters[FIRST_PART_OF_PARAM];
        Project project = new Project(projectName);
        String taskDescription = commandParameters[REST_OF_PARAMS];

        projectsToTasks.addTask(nextTaskId(), project, taskDescription);
    }

    private void addProject(String projectName) {
        projectsToTasks.add(new Project(projectName), new Tasks());
    }

    private void check(String idString) {
        projectsToTasks.setDone(idString, DONE);
    }

    private void unCheck(String idString) {
        projectsToTasks.setDone(idString, NOT_DONE);
    }

    private void help() {
        screen.println("Commands:");
        screen.println("  show");
        screen.println("  add project <project name>");
        screen.println("  add task <project name> <task description>");
        screen.println("  check <task ID>");
        screen.println("  uncheck <task ID>");
        screen.println();
    }

    private void error(Command command) {
        screen.printf(ERROR_COMMAND_NOT_FOUND, command);
        screen.println();
    }

    private long nextTaskId() {
        return ++lastId;
    }
}