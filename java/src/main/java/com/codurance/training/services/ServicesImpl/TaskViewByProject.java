package com.codurance.training.services.ServicesImpl;


import com.codurance.training.services.TaskView;
import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TaskViewByProject implements TaskView {
    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public TaskViewByProject(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void view() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s, createdDate: %s, deadline:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getCreatedDate(), task.getDeadLine());
            }
            out.println();
        }
    }
}
