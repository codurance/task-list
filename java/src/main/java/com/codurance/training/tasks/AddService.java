package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface AddService {
    void add(Map<String, List<Task>> tasks, String commandLine, PrintWriter out);

}
