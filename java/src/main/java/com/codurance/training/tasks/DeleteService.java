package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public interface DeleteService {
    void deleteTask(Map<Long, Project> projectList, List<String> taskIds, String command, PrintWriter out);
}
