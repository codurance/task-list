package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.IExecuteTaskService;
import com.codurance.training.tasks.service.IRunTaskService;
import com.codurance.training.tasks.service.Impl.ExecuteTaskServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class RunTaskServiceImpl implements IRunTaskService, Runnable{
    private static final String QUIT = "quit";

    private Map<String, List<Task>> tasks;
    private BufferedReader in;
    private PrintWriter out;

    private IExecuteTaskService executeTaskService;

    public RunTaskServiceImpl(Map<String, List<Task>> tasks, BufferedReader in, PrintWriter out) {
        this.tasks = tasks;
        this.in = in;
        this.out = out;
        executeTaskService = new ExecuteTaskServiceImpl(tasks, this.in, this.out);
    }

    @Override
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
            executeTaskService.execute(command);
        }
    }
}
