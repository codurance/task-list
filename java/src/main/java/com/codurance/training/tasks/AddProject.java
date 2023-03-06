package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddProject implements AddService{

    @Override
    public void add(Map<String, List<Task>> tasks, String project, PrintWriter out) {
        tasks.put(project, new ArrayList<Task>());
    }
}