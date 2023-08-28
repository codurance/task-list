package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ToggleTaskImpl implements ToggleTask{

    private  Map<String, List<Task>> tasks;
    private final PrintWriter out = new PrintWriter(System.out);

    public ToggleTaskImpl(Map<String, List<Task>> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void toggle(String idString, boolean value) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(value);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}
