package com.codurance.training.tasks.service;

import com.codurance.training.tasks.Task;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskListAddServiceImpl implements TaskListAddService {

    private final Map<String, List<Task>> tasks;
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;

    public TaskListAddServiceImpl(Map<String, List<Task>> tasks, BufferedReader in, PrintWriter out) {
        this.tasks = tasks;
        this.in = in;
        this.out = out;
    }

    @Override
    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    @Override
    public void addProject(String name) {
        this.tasks.put(name, new ArrayList<Task>());
    }

    @Override
    public void addTask(String project, String description) {
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
