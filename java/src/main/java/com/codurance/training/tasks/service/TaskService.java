package com.codurance.training.tasks.service;

import java.io.PrintWriter;

public interface TaskService {


    void add(PrintWriter out, String commandLine) ;

    void check(PrintWriter out, String idString);

    void uncheck(PrintWriter out, String idString);


    void addDeadline(PrintWriter out, String commandLine);

    void delete(PrintWriter out, String commandLine);
}
