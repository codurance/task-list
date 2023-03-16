package com.codurance.training.services.ServicesImpl;

import com.codurance.training.services.TaskView;
import com.codurance.training.tasks.Task;
import com.codurance.training.util.DateFormatter;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TaskViewByDeadline implements TaskView {
    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public TaskViewByDeadline(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void view() {
        Comparator<Task> compareByDate = Comparator.comparing(p -> DateFormatter.parseDate(p.getDeadLine()));
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = project.getValue();
            Collections.sort(newTasks, compareByDate);
            for (Task task : newTasks) {
                out.printf("    [%c] %s: %s, createdDate: %s, deadline:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getCreatedDate(),  task.getDeadLine());
            }
            out.println();
        }
    }
}