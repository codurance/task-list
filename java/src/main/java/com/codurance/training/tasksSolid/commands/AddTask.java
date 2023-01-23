package com.codurance.training.tasksSolid.commands;

import com.codurance.training.tasksSolid.Task;
import com.codurance.training.tasksSolid.TaskContainer;

import java.io.PrintWriter;
import java.util.List;

public class AddTask {
    private final PrintWriter out;

    private long lastId = 0;

    public AddTask(PrintWriter out) {
        this.out = out;
    }

    public void addTaskToProject(String project, String description) {
        List<Task> projectTasks = TaskContainer.getTasks().get(project);
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
