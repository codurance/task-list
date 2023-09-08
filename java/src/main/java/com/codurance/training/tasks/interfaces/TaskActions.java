package com.codurance.training.tasks.interfaces;
public interface TaskActions {
    void show();
    void add(String commandLine);
    void addProject(String name);
    void addTask(String project, String description);
    void check(String idString);
    void uncheck(String idString);
    void setDone(String idString, boolean done);
    long nextId();
}