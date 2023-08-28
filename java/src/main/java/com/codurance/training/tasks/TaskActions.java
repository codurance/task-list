package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

public interface TaskActions {

    Map<String, List<Task>> getTasks();
    void addProject(String name);
    void addTask(String project, String description);

    void setDone(String idString, boolean done);
}
