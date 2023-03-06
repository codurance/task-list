package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Uncheck implements CheckOrUncheckService{



    @Override
    public void check(Map<String, List<Task>> tasks, String idString, PrintWriter out) {
    }

    @Override
    public void unCheck(Map<String, List<Task>> tasks, String idString, PrintWriter out) {
        setDone(idString, false, tasks);
    }
}
