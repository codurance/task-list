package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Utility {

    private  BufferedReader in;
    public  PrintWriter out;
    private Map<String, List<Task>> tasks;

    public Utility(BufferedReader in, PrintWriter out,  Map<String, List<Task>> tasks) {
        this.in = in;
        this.out = out;
        this.tasks = tasks;
    }

    public void setDone(String idString, boolean done) {
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
