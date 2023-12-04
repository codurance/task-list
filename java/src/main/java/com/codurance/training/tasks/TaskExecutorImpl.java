package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class TaskExecutorImpl implements TaskExecutor{

    private final TaskManager taskManager;
    private final BufferedReader in;
    private final PrintWriter out;
    public TaskExecutorImpl(BufferedReader reader, PrintWriter writer,TaskManager taskManager) {
        this.taskManager = taskManager;
        this.in = reader;
        this.out = writer;
    }

    @Override
    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskManager.show();
                break;
            case "add":
                taskManager.add(commandRest[1]);
                break;
            case "check":
                taskManager.check(commandRest[1]);
                break;
            case "uncheck":
                taskManager.uncheck(commandRest[1]);
                break;
            case "help":
                taskManager.help();
                break;
            default:
                taskManager.error(command);
                break;
        }
    }
}
