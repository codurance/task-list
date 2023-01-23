package com.codurance.training.tasksSolid.commands;

import com.codurance.training.tasksSolid.manager.Task;
import com.codurance.training.tasksSolid.manager.TaskContainer;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ShowTask {
    private final PrintWriter out;

    public ShowTask(PrintWriter out) {
        this.out = out;
    }

    public void printAllTaskDetails() {
        for (Map.Entry<String, List<Task>> project : TaskContainer.getTasks().entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}
