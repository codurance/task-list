package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class Project {
    String projectName;

    List<Task> tasks ;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    Project(String projectName){
        this.projectName = projectName;
        tasks = new ArrayList<>();
    }
}
