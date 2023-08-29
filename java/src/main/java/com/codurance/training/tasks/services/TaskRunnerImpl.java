package com.codurance.training.tasks.services;

import com.codurance.training.tasks.entities.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskRunnerImpl implements TaskRunnerInterface {

    private static final String QUIT = "quit";
    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();

    public void run(PrintWriter out, BufferedReader in,int lastId) {
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
            execute(command,lastId);
        }
    }

    private void execute(String commandLine,int lastId) {

        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        TaskOperationImpl operationImpl = new TaskOperationImpl(tasks);
        Project project = new Project();
        Checker checkerOn = new Checkon();
        Checker checkerOff = new Checkoff();
        PrintWriter out = new PrintWriter(System.out);
        switch (command) {
            case "show":
                operationImpl.show(out);
                break;
            case "add":
                operationImpl.add(project,lastId);
                break;
            case "check":
                operationImpl.check(checkerOn);
                break;
            case "uncheck":
                operationImpl.check(checkerOff);
                break;
            case "help":
                operationImpl.help();
                break;
            default:
                operationImpl.error(command);
                break;
        }
    }

}