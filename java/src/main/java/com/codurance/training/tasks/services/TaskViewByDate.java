package com.codurance.training.tasks.services;

import com.codurance.training.tasks.Helper;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.interfaces.ViewProject;

import java.io.PrintWriter;
import java.util.*;

public class TaskViewByDate implements ViewProject {

    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public TaskViewByDate(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void view() {
        Comparator<Task> compareByDate = Comparator.comparing(p -> Helper.formatDate(p.getCreatedAt()));
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = project.getValue();
            Collections.sort(newTasks, compareByDate);
            for (Task task : newTasks) {
                out.printf("    [%c] %s: %s, deadline: %s, createdDate:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline(), task.getCreatedAt());
            }
            out.println();
        }
    }
}
