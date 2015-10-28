package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static com.codurance.training.tasks.Task.NOT_DONE;

public final class TaskList implements Runnable {
    private static final String COMMAND_SEPARATOR = " ";

    private static final String COMMAND_PROMPT = "> ";

    private static final String CMD_QUIT = "quit";
    private static final String CMD_SHOW = "show";
    private static final String CMD_ADD = "add";
    private static final String CMD_CHECK = "check";
    private static final String CMD_UNCHECK = "uncheck";
    private static final String CMD_HELP = "help";

    private static final String SUB_CMD_PROJECT = "project";
    private static final String SUB_CMD_TASK = "task";

    private static final String ERROR_COMMAND_NOT_FOUND = "I don't know what the command \"%s\" is.";

    private static final boolean DONE = true;
    private static final boolean KEEP_RUNNING = DONE;

    private static final int TIMES_TO_APPLY_SEPARATOR = 2;

    private final BufferedReader in;
    private final PrintWriter out;
    private final ProjectsToTasks projectsToTasks = new ProjectsToTasks();
    private int lastId;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (KEEP_RUNNING) {
            out.print(COMMAND_PROMPT);
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(CMD_QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(COMMAND_SEPARATOR, TIMES_TO_APPLY_SEPARATOR);
        String command = commandRest[0];
        switch (command) {
            case CMD_SHOW:
                projectsToTasks.show(out);
                break;
            case CMD_ADD:
                add(commandRest[1]);
                break;
            case CMD_CHECK:
                check(commandRest[1]);
                break;
            case CMD_UNCHECK:
                unCheck(commandRest[1]);
                break;
            case CMD_HELP:
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void add(String commandLine) {
        String[] subCommandRest = commandLine.split(COMMAND_SEPARATOR, TIMES_TO_APPLY_SEPARATOR);
        String subCommand = subCommandRest[0];
        if (subCommand.equals(SUB_CMD_PROJECT)) {
            addProject(subCommandRest[1]);
        } else if (subCommand.equals(SUB_CMD_TASK)) {
            String[] projectTask = subCommandRest[1].split(COMMAND_SEPARATOR, TIMES_TO_APPLY_SEPARATOR);
            projectsToTasks.addTask(nextTaskId(), projectTask[0], projectTask[1], out);
        }
    }

    private void addProject(String projectName) {
        projectsToTasks.add(projectName, new Tasks());
    }

    private void check(String idString) {
        projectsToTasks.setDone(idString, DONE, out);
    }

    private void unCheck(String idString) {
        projectsToTasks.setDone(idString, NOT_DONE, out);
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf(ERROR_COMMAND_NOT_FOUND, command);
        out.println();
    }

    private long nextTaskId() {
        return ++lastId;
    }
}