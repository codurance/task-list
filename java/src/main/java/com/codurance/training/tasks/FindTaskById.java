package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindTaskById {
    Map<String, Project> addedProjects ;

    public FindTaskById(Map<String, Project> addedProjects) {
        this.addedProjects = addedProjects;
    }

    public Task getTask(String taskId){
        int taskID = Integer.parseInt(taskId);
        for (Map.Entry<String, Project> project : addedProjects.entrySet()) {
            for (Task task : project.getValue().getTasks()) {
                if (task.getId() == taskID) {
                    return task;
                }
            }
        }
        return null;
    }

    public Project getProject(String taskId){
        int taskID = Integer.parseInt(taskId);
        for (Map.Entry<String, Project> project : addedProjects.entrySet()) {
            for (Task task : project.getValue().getTasks()) {
                if (task.getId() == taskID) {
                    return project.getValue();
                }
            }
        }
        return null;
    }
}
