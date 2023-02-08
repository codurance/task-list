package com.codurance.training.tasks;

import java.io.*;
import java.util.*;

public class ManageProjectDetails {

    private final AddTask addTask;

    private Map<String, List<Task>> tasks;

    private PrintWriter out;

    public ManageProjectDetails(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
        this.addTask = new AddTaskService(tasks, out);
    }

    public void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask.addTask(projectTask[0], projectTask[1]);
        }
    }

    public void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

}
