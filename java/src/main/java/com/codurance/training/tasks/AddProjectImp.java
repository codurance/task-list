package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddProjectImp implements TaskService{

    private final Map<String, List<Task>> tasks;

    public AddProjectImp(Map<String, List<Task>> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void add(String name) {
        tasks.put(name, new ArrayList<>());
    }
}
