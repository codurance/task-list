package com.codurance.training.tasks.services;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.interfaces.AddProject;
import com.codurance.training.tasks.interfaces.AddService;
import com.codurance.training.tasks.interfaces.AddTask;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddServiceImpl implements AddProject, AddService, AddTask {
    private Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private PrintWriter out;
    private long lastId = 0;

    public AddServiceImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    private long getNextId() {
        return ++lastId;
    }

    private boolean testValidation(String id) {
        if (id.contains(" "))
            return false;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(id);
        return !(matcher.find());
    }


    @Override
    public void addProject(String name) {
        tasks.put(name, new ArrayList<>()); // Adding new project in the map
    }

    @Override
    public void add(String command) {
        String[] subcommandRest = command.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ");
            if (projectTask.length == 4) {
                try {
                    addTask(projectTask[1], projectTask[0], projectTask[2], new SimpleDateFormat("dd-MM-yyyy").parse(projectTask[3]));
                } catch (RuntimeException | ParseException e) {
                    throw new RuntimeException(e);
                }
            } else {
                addTask(projectTask[1], projectTask[0], projectTask[2], null);
            }
        }

    }

    @Override
    public void addTask(String taskId, String projectName, String description, Date deadline) {
        List<Task> projectTasks = tasks.get(projectName);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", projectName);
            out.println();
            return;
        }
        if (testValidation(taskId)) {
            projectTasks.add(new Task(taskId, description, false, deadline, new Date()));
        } else {
            out.printf("Id provided does not match the criteria :", taskId);
            out.printf("Will use auto increment logic to add respective task...", String.valueOf(getNextId()));
            projectTasks.add(new Task(String.valueOf(getNextId()), description, false, deadline, new Date()));
        }

    }
}
