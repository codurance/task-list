package com.codurance.training.operations.manage;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface ManageTaskInterface {
    void manage(PrintWriter out, String idString, boolean done, Map<String, List<Task>> tasks);
}
