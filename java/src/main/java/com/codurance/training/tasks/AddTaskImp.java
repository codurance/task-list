package com.codurance.training.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class AddTaskImp implements TaskService{

    private final Map<String, List<Task>> tasks;
    private long lastId;

    public AddTaskImp(Map<String, List<Task>> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void add(String commandLine) {
        String[] projectTask = commandLine.split(" ", 3);
        String project = projectTask[0];
        String description = projectTask[1];
        String taskId = projectTask[2];
        if (taskId.contains(" ")) {
            out.println("New ID cannot contain spaces.");
            return;
        }
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(taskId, description, false));
    }
}
