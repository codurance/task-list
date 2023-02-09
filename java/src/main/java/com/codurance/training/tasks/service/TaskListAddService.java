package com.codurance.training.tasks.service;

public interface TaskListAddService extends TaskListService {
    void add(String commandLine);
    void addProject(String name);
    void addTask(String project, String description);
}
