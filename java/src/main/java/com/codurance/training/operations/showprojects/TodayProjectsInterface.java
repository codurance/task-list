package com.codurance.training.operations.showprojects;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface TodayProjectsInterface {
    void show(PrintWriter out, Map<String, List<Task>> tasks);
}
