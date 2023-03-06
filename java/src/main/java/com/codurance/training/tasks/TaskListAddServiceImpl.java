package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.PrintWriter;


public class TaskListAddServiceImpl implements TaskListAddService{

    private TaskList taskList;

    private long lastId = 0;

    private BufferedReader in;
    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;


    public TaskListAddServiceImpl(PrintWriter out,  Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
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
        tasks.put(name, new ArrayList<Task>());
    }

    @Override
    public void addTask(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(this.nextId(), description, false));
    }

    public long nextId() {
        return ++lastId;
    }
}
