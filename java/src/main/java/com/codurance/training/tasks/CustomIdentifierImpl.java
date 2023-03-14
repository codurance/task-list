package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static com.codurance.training.tasks.TaskList.tasks;

public class CustomIdentifierImpl implements AddService{
    @Override
    public void CRUD(String commandLine, PrintWriter out) {
        String [] commandLineSplit = commandLine.split(" ",2);
        int taskId = Integer.parseInt(commandLineSplit[0]);
        String customid = commandLineSplit[1];
        if (!customid.matches("[a-zA-Z0-9]+")) {
            out.println("Invalid custom id.Only alphanumeric characters are allowed.");
            return;
        }
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == taskId) {
                    task.setCustomId(customid);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", taskId);
    }
}
