package com.codurance.training.services.ServicesImpl;

import com.codurance.training.services.Check;
import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheckCommandImpl implements Check {
    private Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private PrintWriter out;

    public CheckCommandImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void check(String idString) {
        setDone(idString, true);
    }

    @Override
    public void unCheck(String idString) {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().equals(id)) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}