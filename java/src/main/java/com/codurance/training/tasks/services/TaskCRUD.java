package com.codurance.training.tasks.services;

import com.codurance.training.tasks.entities.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

class TaskCRUD extends Item {


    public void add(Map<String, List<Task>> tasks, String project, String description, PrintWriter out, int lastId) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextId(lastId), description, false));
    }

    private long nextId(int lastId) {
        return ++lastId;
    }
}

