package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final long id;
    private final String name;
    private final List<Task> tasks = new ArrayList<>();

    public Project(long id,String name) {
        this.id=id;
        this.name=name;
    }

    public long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
