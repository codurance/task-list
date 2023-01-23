package com.codurance.training.tasksSolid.commands;

import com.codurance.training.tasksSolid.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class AddTask {
    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;

    private long lastId = 0;

    public AddTask(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    public void addTaskToProject(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    private long nextId() {
        return ++lastId;
    }
}
