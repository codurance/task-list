package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TaskViewerImpl implements TaskViewer{
    private  Map<String, List<Task>> tasks;
    private  PrintWriter out;
    public TaskViewerImpl( PrintWriter out, Map<String, List<Task>> tasks) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                System.out.println(task.getDescription());
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}
