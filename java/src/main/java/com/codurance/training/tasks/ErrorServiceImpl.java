package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ErrorServiceImpl implements ErrorService{

    private final PrintWriter out;

    public ErrorServiceImpl(PrintWriter out,  Map<String, List<Task>> tasks) {
        this.out = out;
    }

    @Override
    public void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
