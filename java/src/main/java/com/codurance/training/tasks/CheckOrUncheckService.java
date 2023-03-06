package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface CheckOrUncheckService {
    default void setDone(String idString, boolean done, Map<String, List<Task>> tasks) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
    }
    void check(Map<String, List<Task>> tasks, String idString, PrintWriter out);
    void unCheck(Map<String, List<Task>> tasks, String idString,PrintWriter out);
}



