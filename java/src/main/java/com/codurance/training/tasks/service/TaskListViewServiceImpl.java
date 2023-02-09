package com.codurance.training.tasks.service;

import com.codurance.training.tasks.Task;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TaskListViewServiceImpl implements TaskListViewService {

    private final Map<String, List<Task>> tasks;
    private final BufferedReader in;
    private final PrintWriter out;

    public TaskListViewServiceImpl(Map<String, List<Task>> tasks, BufferedReader in, PrintWriter out) {
        this.tasks = tasks;
        this.in = in;
        this.out = out;
    }

    @Override
    public void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    @Override
    public void listTasksDueToday() throws ParseException {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (new SimpleDateFormat("dd/MM/yyyy").format(task.getDeadline())
                        .equals((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()))){
                    out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                }
            }
        }
    }
}
