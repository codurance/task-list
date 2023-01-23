package com.codurance.training.tasksSolid.commands;

import com.codurance.training.tasksSolid.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ShowTask {
    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;

    public ShowTask(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    public void printAllTaskDetails() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}
