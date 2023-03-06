package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ViewByDeadline implements ShowService{

    @Override
    public void show(Map<String, List<Task>> tasks, PrintWriter out) {
        Comparator<Task> dateComparator = Comparator.comparing(Task::getDeadline);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> sortedTasks = project.getValue();
            sortedTasks.sort(dateComparator);
            for (Task task : sortedTasks) {
                out.println(task.toString());
            }
            out.println();
        }
    }
}
