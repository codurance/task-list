package com.codurance.training.tasks.services;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.interfaces.DeleteTask;

import java.io.PrintWriter;
import java.util.*;

public class DeleteTaskService implements DeleteTask {

    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public DeleteTaskService(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void deleteTask(String id) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            if (project.getValue().removeIf(task -> task.getId().equals(id))) {
                out.printf("Deleted task with id: %s", id);
            } else {
                out.printf("No task found with this id =>  %s", id);
            }
        }
        out.println();
    }
}
