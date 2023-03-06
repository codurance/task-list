package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddDelete  implements DeleteInterface{
    @Override
    public void delete(Map<String, List<Task>> tasks, String idString, PrintWriter out) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId()==Long.parseLong(idString)) {
                    project.getValue().remove(task);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.", idString);
        out.println();
    }
}
