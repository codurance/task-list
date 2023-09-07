package com.codurance.training.tasks.actions;

public interface TaskActionHandler {
    void show();

    void add(String commandLine);
    void addProject(String name);
     void check(String idString);

     void uncheck(String idString);

    void setDone(String idString, boolean done);

    void help();

    void error(String command);

}
