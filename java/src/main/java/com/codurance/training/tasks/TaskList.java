package com.codurance.training.tasks;

import com.codurance.training.tasks.service.*;
import com.codurance.training.tasks.utils.TaskListUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    private TaskListViewService viewService;
    private TaskListAddService addService;
    private TaskListUpdateService updateService;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        viewService = new TaskListViewServiceImpl(tasks, in, out);
        addService = new TaskListAddServiceImpl(tasks, in, out);
        updateService = new TaskListUpdateServiceImpl(tasks, in, out);
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            try {
                execute(command);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void execute(String commandLine) throws ParseException {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                viewService.show();
                break;
            case "add":
                addService.add(commandRest[1]);
                break;
            case "check":
                updateService.check(commandRest[1]);
                break;
            case "uncheck":
                updateService.uncheck(commandRest[1]);
                break;
            case "deadline":
                updateService.setDeadline(commandRest[1]);
                break;
            case "today":
                viewService.listTasksDueToday();
                break;
            case "help":
                TaskListUtils.help(out);
                break;
            default:
                error(command);
                break;
        }
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}