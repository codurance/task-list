package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CheckAndUncheckServiceImpl implements CheckAndUncheckService{

    private final Utility utility;
    private final BufferedReader in;
    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;

    public CheckAndUncheckServiceImpl(BufferedReader in, PrintWriter out, Map<String, List<Task>> tasks) {
        this.in = in;
        this.out = out;
        this.tasks = tasks;
        this.utility = new Utility(this.in,this.out,this.tasks);
    }

    @Override
    public void check(String idString) {
        utility.setDone(idString, true);
    }

    @Override
    public void uncheck(String idString) {
        utility.setDone(idString, false);
    }
}
