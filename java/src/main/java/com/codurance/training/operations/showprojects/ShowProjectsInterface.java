package com.codurance.training.operations.showprojects;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface ShowProjectsInterface {
    void byProject(PrintWriter out, Map<String, List<Task>> tasks);
    void byDate(PrintWriter out,Map<String, List<Task>> tasks);
    void byDeadline(PrintWriter out,Map<String, List<Task>> tasks);
}
