package com.codurance.training.services.ServicesImpl;

import com.codurance.training.services.Delete;
import com.codurance.training.tasks.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class DeleteImpl implements Delete {
    private Map<String, List<Task>> tasks ;
    private PrintWriter out;

    public DeleteImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void deleteTask(String taskId) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            if(project.getValue().removeIf(task -> task.getId().equals(taskId))){
                out.printf("Deleted task with id: %s", taskId);
            }
            else{
                out.printf("Not found task with id: %s", taskId);
            }
        }
        out.println();
    }
}
