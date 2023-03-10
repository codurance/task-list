package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.Map;

public class AddProject implements AddService {

    private long projectId = 0;
    @Override
    public boolean add(Map<Long, Project> projectList, String projectName, PrintWriter out) {
        projectId += 1;
        projectList.put(projectId, new Project(projectId, projectName));
        return true;
    }
}
