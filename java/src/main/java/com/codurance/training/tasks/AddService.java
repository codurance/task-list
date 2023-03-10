package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.Map;

public interface AddService {
    boolean add(Map<Long, Project> projectList, String commandLine, PrintWriter out);
}
