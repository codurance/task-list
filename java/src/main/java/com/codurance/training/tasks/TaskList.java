package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    private static final String PRINT_LINE_FORMATTER = "    [%c] %d: %s%n";
    private static final String COULD_NOT_FIND_A_PROJECT = "Could not find a project with the name \"%s\".";
    private static final String COULD_NOT_FIND_A_TASK = "Could not find a task with an ID of %d.";
    private static final String ERROR_COMMAND_NOT_FOUND = "I don't know what the command \"%s\" is.";

    private static final char TASK_DONE_SYMBOL = 'x';
    private static final char TASK_NOT_DONE_SYMBOL = ' ';

    private static final boolean DONE = true;
    private static final boolean KEEP_RUNNING = DONE;
    private static final boolean NOT_DONE = false;

    private static final int TIMES_TO_APPLY_PATTERN = 2;

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;

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
        String[] commandRest = commandLine.split(COMMAND_SEPARATOR, TIMES_TO_APPLY_PATTERN);
        String command = commandRest[0];
        switch (command) {
            case CMD_SHOW:
                show();
                break;
            case CMD_ADD:
                add(commandRest[1]);
                break;
            case CMD_CHECK:
                check(commandRest[1]);
                break;
            case CMD_UNCHECK:
                uncheck(commandRest[1]);
                break;
            case CMD_HELP:
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf(PRINT_LINE_FORMATTER, (task.isDone() ? TASK_DONE_SYMBOL : TASK_NOT_DONE_SYMBOL), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(COMMAND_SEPARATOR, TIMES_TO_APPLY_PATTERN);
        String subcommand = subcommandRest[0];
        if (subcommand.equals(SUB_CMD_PROJECT)) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals(SUB_CMD_TASK)) {
            String[] projectTask = subcommandRest[1].split(COMMAND_SEPARATOR, TIMES_TO_APPLY_PATTERN);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    private void addTask(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf(COULD_NOT_FIND_A_PROJECT, project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextId(), description, NOT_DONE));
    }

    private void check(String idString) {
        setDone(idString, DONE);
    }

    private void uncheck(String idString) {
        setDone(idString, NOT_DONE);
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf(COULD_NOT_FIND_A_TASK, id);
        out.println();
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

    private long nextId() {
        return ++lastId;
    }
}