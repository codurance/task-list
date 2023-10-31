package com.codurance.training.tasks.tasklist;

import com.codurance.training.tasks.task.ITask;

import java.util.List;
import java.util.Map;

public interface ITaskList {
    void addProject(String name);
    void addTask(String project, String description);
    void check(String idString);
    void uncheck(String idString);
    Map<String, List<ITask>> getTasks();
}
