package com.codurance.training.tasks;

public interface TaskManager {
    void add(String commandLine);
    void show();
    void check(String idString);
    void uncheck(String idString);
    void help();
    void error(String command);


}