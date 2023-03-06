package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Today implements ShowService {
    @Override
    public void show(Map<String, List<Task>> tasks, PrintWriter out) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                if (task.getDeadline() != null && task.getDeadline().toString().equals(formatter.format(new Date()))) {
                    out.println(task.getDescription());
                }
            }
            out.println();
        }
    }
}
