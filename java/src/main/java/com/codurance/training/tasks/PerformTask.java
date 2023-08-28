package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class PerformTask {
    private  Map<String, List<Task>> tasks;
    private final BufferedReader in;
    private final PrintWriter out;
    private Error errors;

    private AddToTask addToTask;
    private ToggleTask toggleTask;

    private Helper helper;

    private TaskViewer taskViewer;

    public PerformTask(BufferedReader in, PrintWriter out, Map<String, List<Task>> tasks) {
        this.in = in;
        this.out = out;
        this.tasks = tasks;
    }

    public void executeTask(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskViewer = new TaskViewerImpl(out, tasks);
                taskViewer.show();
                break;
            case "add":
                addToTask = new AddToTaskImpl(tasks);
                addToTask.add(commandRest[1]);
                break;
            case "check":
                toggleTask = new ToggleTaskImpl(tasks);
                toggleTask.toggle(commandRest[1], true);
                break;
            case "uncheck":
                toggleTask = new ToggleTaskImpl(tasks);
                toggleTask.toggle(commandRest[1], false);
                break;
            case "help":
                helper = new HelperImpl();
                helper.help();
                break;
            default:
                errors = new ErrorImpl();
                errors.showError(command);
                break;
        }
    }
}

