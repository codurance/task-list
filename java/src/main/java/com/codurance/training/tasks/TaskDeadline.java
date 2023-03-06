package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TaskDeadline {
    public void setDeadline(String commandLine, PrintWriter out, Map<String, List<Task>> tasks) {

        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        int id = Integer.parseInt(subcommand);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDeadLine(subcommandRest[1]);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}
