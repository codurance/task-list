package com.codurance.training.tasks.services;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.interfaces.ShowProject;

import java.io.PrintWriter;
import java.util.*;

public class ShowService implements ShowProject {
    private Map<String, List<Task>> tasks = new LinkedHashMap<>();

    private PrintWriter out;

    public ShowService(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s, deadline: %s, createdDate:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline(), task.getCreatedAt());
            }
            out.println();
        }
    }

}
