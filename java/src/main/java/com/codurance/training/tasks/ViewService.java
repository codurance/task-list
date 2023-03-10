package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.Map;

public interface ViewService {
    void viewTasksByProject(Map<Long, Project> projectList, PrintWriter out);
    void viewTasksDueToday(Map<Long, Project> projectList, PrintWriter out);
    void viewTasksByDate(Map<Long, Project> projectList, PrintWriter out);
    void viewTasksByDeadline(Map<Long, Project> projectList, PrintWriter out);
}
