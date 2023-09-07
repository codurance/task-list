package com.codurance.training.actions;

 public interface TaskActions {
     void show();

     void add(String commnandLine);

     void addProject(String name);

     void setDone(String idString,boolean done);

     void check(String idString);

     void uncheck(String idString);

     void help();

     void error(String command);
}
