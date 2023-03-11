package com.codurance.training.operations.customize;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface CustomizeIdInterface {
    void customize(PrintWriter out,String id, String newId, Map<String, List<Task>> tasks);
}
