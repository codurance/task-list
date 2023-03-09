package com.codurance.training.tasks;

import java.util.*;

public class TasksByProject {
    Map<String, Project> addedProjects ;

    public TasksByProject(Map<String, Project> addedProjects) {
        this.addedProjects = addedProjects;
    }

    public List<Task> getTaskByProject(String projectName){
        List<Task> tasks = new ArrayList<>();
        for (Task task : addedProjects.get(projectName).getTasks()) {
            tasks.add(task);
        }
        return tasks;
    }
}
