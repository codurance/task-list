package com.codurance.training.operations.adder;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface AdderInterface {
    void addProject(String name, Map<String, List<Task>> tasks);
    void addTask(PrintWriter out,long lastId, String project, String description, Map<String, List<Task>> tasks);
}
