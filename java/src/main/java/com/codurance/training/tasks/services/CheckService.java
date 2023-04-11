package com.codurance.training.tasks.services;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.interfaces.CheckTask;

import java.io.PrintWriter;
import java.util.*;

public class CheckService implements CheckTask {

    private Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private PrintWriter out;

    public CheckService(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void check(String id) {
        setDone(id, true);
    }

    @Override
    public void uncheck(String id) {
        setDone(id, false);
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
        out.printf("No task exists with this ID :  %d.", id);
//        out.println();
    }
}
