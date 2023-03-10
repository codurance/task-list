package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.Map;

public interface StatusService {
    void changeStatus(Map<Long, Project> projectList, String idString, boolean done, PrintWriter out);
}
