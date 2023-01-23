package com.codurance.training.tasksSolid.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public final class TaskRunner implements Runnable {
    private static final String QUIT = "quit";

    private final BufferedReader in;
    private final PrintWriter out;
    private final TaskExecutor taskExecutor;

    public TaskRunner(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        taskExecutor = new TaskExecutor(out);
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
            taskExecutor.execute(command);
        }
    }
}
