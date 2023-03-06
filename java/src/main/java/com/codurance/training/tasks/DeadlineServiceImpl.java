package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class DeadlineServiceImpl implements DeadlineService{

    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;

    public DeadlineServiceImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }


    @Override
    public void addDeadline(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        long taskId = Long.parseLong(subcommandRest[0]);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == taskId) {
                    task.setDeadline(subcommandRest[1]);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", taskId);
        out.println();
    }
}
