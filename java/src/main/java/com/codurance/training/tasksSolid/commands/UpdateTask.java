package com.codurance.training.tasksSolid.commands;

import com.codurance.training.tasksSolid.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class UpdateTask {

    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;

    public UpdateTask(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    public void check(String idString) {
        setDone(idString, true);
    }

    public void uncheck(String idString) {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}
