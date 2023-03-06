package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface Show {
    public void show(PrintWriter out, Map<String, List<Task>> tasks);
}
