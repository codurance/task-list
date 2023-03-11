package com.codurance.training.task;

import java.io.PrintWriter;
import java.text.ParseException;

public interface TaskListInterface {
    void showTaskList(PrintWriter out,String commandLine);
    void add(PrintWriter out,String commandLine);
    void manageTask(PrintWriter out,String commandLine,boolean status);
    void manageDeadline(String commandLine) throws ParseException;
    void showTodayProjects(PrintWriter out);
    void help(PrintWriter out);
    void error(PrintWriter out,String commandLine);
    void customizeId(PrintWriter out,String commandLine);
    void deleteId(String commandLine);
}
