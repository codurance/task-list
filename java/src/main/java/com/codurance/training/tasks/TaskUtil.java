package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TaskUtil extends TaskData{
    public long nextId() {
        return ++lastId;
    }

    public void setDone(String idString, boolean done, PrintWriter out) {
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
