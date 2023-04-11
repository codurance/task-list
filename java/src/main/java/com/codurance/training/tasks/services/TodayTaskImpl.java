package com.codurance.training.tasks.services;

import com.codurance.training.tasks.Helper;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.interfaces.TodayTasks;

import java.io.PrintWriter;
import java.util.*;

public class TodayTaskImpl implements TodayTasks {

    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public TodayTaskImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void displayTodayDueTasks() {
        Date today = new Date();
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                if (task.getDeadline() != null && Helper.formatDate(task.getDeadline()).equals(Helper.formatDate(today))) {
                    out.printf("    [%c] %s: %s, deadline: %s, createdDate:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline(), task.getCreatedAt());
                }
            }
            out.println();
        }
    }
}
