package com.codurance.training.tasks;

import java.io.*;
import java.util.*;

public class AddTaskService implements AddTask {

    Map<String, List<Task>> tasks;
    PrintWriter out;

    private Long lastId;

    public AddTaskService(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out=  out;
        this.lastId = 0l;
    }

    public void addTask(String project, String description) {
        if(tasks != null && !tasks.isEmpty()) {
            List<Task> projectTasks = tasks.get(project);
            if (projectTasks == null) {
                out.printf("Could not find a project with the name \"%s\".", project);
                out.println();
                return;
            }
            projectTasks.add(new Task(nextId(), description, false));
        }
    }

    public long nextId() {
        return ++lastId;
    }

}
