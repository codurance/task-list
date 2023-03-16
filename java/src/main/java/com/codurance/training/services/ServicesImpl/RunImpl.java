package com.codurance.training.services.ServicesImpl;

import com.codurance.training.services.Execute;
import com.codurance.training.services.Run;
import com.codurance.training.tasks.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class RunImpl implements Run , Runnable {

    private static final String QUIT = "quit";

    private Map<String, List<Task>> tasks;
    private BufferedReader in;
    private PrintWriter out;

    public RunImpl(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    private Execute executionService;


    public RunImpl(Map<String, List<Task>> tasks, BufferedReader in, PrintWriter out) {
        this.tasks = tasks;
        this.in = in;
        this.out = out;
        executionService = new ExecuteImpl(tasks, this.in, this.out);
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
            executionService.execute(command);
        }
    }
}