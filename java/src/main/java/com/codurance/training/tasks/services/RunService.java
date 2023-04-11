package com.codurance.training.tasks.services;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.interfaces.ExecuteProject;
import com.codurance.training.tasks.interfaces.RunProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class RunService implements Runnable, RunProject {
    private static final String QUIT = "quit";

    private Map<String, List<Task>> tasks;
    private BufferedReader in;
    private PrintWriter out;

    private ExecuteProject executeTaskService;

    public RunService(Map<String, List<Task>> tasks, BufferedReader in, PrintWriter out) {
        this.tasks = tasks;
        this.in = in;
        this.out = out;
        executeTaskService = new ExecutionService(tasks, this.in, this.out);
    }

    @Override
    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (RuntimeException | IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            executeTaskService.execute(command);
        }
    }
}
