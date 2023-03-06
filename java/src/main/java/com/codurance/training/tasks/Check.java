package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Check implements CheckOrUncheckService {
    @Override
    public void check(Map<String, List<Task>> tasks, String idString, PrintWriter out) {
        new Uncheck().setDone(idString,true,tasks);
    }

    @Override
    public void unCheck(Map<String, List<Task>> tasks, String idString, PrintWriter out) {
    }
}
