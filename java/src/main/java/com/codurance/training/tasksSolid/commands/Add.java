package com.codurance.training.tasksSolid.commands;

import com.codurance.training.tasksSolid.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Add {
    private final Map<String, List<Task>> tasks;
    private final AddTask addTask;

    public Add(Map<String, List<Task>> tasks, AddTask addTask) {
        this.tasks = tasks;
        this.addTask = addTask;
    }

    public void addTaskOrProject(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask.addTaskToProject(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

}
