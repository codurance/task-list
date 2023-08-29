package com.codurance.training.tasks.services;

import com.codurance.training.tasks.entities.Task;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Project extends Item {
     
    public void add(Map<String, List<Task>> tasks, String itemName, String description, PrintWriter out, int lastId) {
        tasks.put(itemName, new ArrayList<Task>());
    }
}