package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final BufferedReader in;
    private final PrintWriter out;

    private final Executor executor;

    public TaskList(BufferedReader reader, PrintWriter writer, Executor commandLineExecutor) {
        this.in = reader;
        this.out = writer;
        this.executor = commandLineExecutor;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        TaskActions taskActions = new TaskActionsImpl();
        Executor commandLineExecutor = new ExecutorImpl(taskActions, out);
        new TaskList(in, out, commandLineExecutor).run();
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
            executor.execute(command);
        }
    }
}
