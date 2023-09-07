package com.codurance.training.tasks.service;

import java.io.PrintWriter;

public interface TaskService {


    public void add(PrintWriter out, String commandLine) ;

    public void check(PrintWriter out, String idString);

    public void uncheck(PrintWriter out, String idString);


    public void addDeadline(PrintWriter out,String commandLine);

}
