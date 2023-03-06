package com.codurance.training.tasks;

public interface TaskListAddService {
    void add(String commandLine);

    void addProject(String name);

    void addTask(String project, String description);

    long nextId();
}
