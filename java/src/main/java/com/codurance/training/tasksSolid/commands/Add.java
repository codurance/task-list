package com.codurance.training.tasksSolid.commands;

import com.codurance.training.tasksSolid.manager.Task;
import com.codurance.training.tasksSolid.manager.TaskContainer;

import java.util.ArrayList;

public class Add {
    private final AddTask addTask;

    public Add(AddTask addTask) {
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
        TaskContainer.getTasks().put(name, new ArrayList<Task>());
    }

}
