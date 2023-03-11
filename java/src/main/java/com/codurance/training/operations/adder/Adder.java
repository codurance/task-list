package com.codurance.training.operations.adder;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Adder implements AdderInterface{
    public void addProject(String name, Map<String, List<Task>> tasks) {
        tasks.put(name, new ArrayList<>());
    }

    public void addTask(PrintWriter out,long lastId, String project, String description, Map<String, List<Task>> tasks) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(Long.toString(lastId), description, false));
    }
}
