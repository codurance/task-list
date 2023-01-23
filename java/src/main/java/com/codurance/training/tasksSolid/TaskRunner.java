package com.codurance.training.tasksSolid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskRunner implements Runnable {
    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();

    private final BufferedReader in;
    private final PrintWriter out;
    private final TaskExecutor taskExecutor;

    public TaskRunner(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        taskExecutor = new TaskExecutor(tasks, out);
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
