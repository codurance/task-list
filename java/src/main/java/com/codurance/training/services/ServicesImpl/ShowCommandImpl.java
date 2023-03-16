package com.codurance.training.services.ServicesImpl;

import com.codurance.training.services.Show;
import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShowCommandImpl implements Show {
    private Map<String, List<Task>> tasks = new LinkedHashMap<>();

    private PrintWriter out;

    public ShowCommandImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("   [%c] %s: %s, createdDate: %s, deadline:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getCreatedDate(), task.getDeadLine());
            }
            out.println();
        }
    }
}
