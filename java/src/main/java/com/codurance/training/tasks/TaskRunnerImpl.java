package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
public class TaskRunnerImpl implements TaskRunner{
    private static final String QUIT = "quit";
    private final BufferedReader in;
    private final PrintWriter out;
    private final TaskManager taskManager;

    public TaskRunnerImpl(BufferedReader reader, PrintWriter writer, TaskManager taskManager) {
        this.in = reader;
        this.out = writer;
        this.taskManager = taskManager;
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
           new TaskExecutorImpl(in,out,taskManager).execute(command);
        }
    }
}
