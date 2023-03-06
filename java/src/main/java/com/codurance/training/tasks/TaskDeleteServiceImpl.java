package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TaskDeleteServiceImpl implements TaskDeleteService{

    private BufferedReader in;
    private PrintWriter out;
    private Map<String, List<Task>> tasks;

    public TaskDeleteServiceImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }
    @Override
    public void deleteTask(String taskId) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == Long.parseLong(taskId)) {
                    project.getValue().remove(task);
                    out.printf("task deleted with this taskId: %s", taskId);
                    return;
                }
            }
        }
        out.printf("No task found with this taskId: %s", taskId);
        out.println();
    }
}
