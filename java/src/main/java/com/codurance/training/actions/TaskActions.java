package com.codurance.training.actions;

 public interface TaskActions {
     void show(String commandLine);

     void add(String commnandLine);

     void addProject(String name);

     void setDone(String idString,boolean done);

     void check(String idString);

     void uncheck(String idString);

     void help();

     void error(String command);

     void deadline(String command);

     void today();

     void delete(String command);

     void showByDeadline();

     void showByDate(String s);

     void customize(String s);
 }
