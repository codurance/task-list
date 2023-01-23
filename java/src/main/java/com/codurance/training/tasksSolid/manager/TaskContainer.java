package com.codurance.training.tasksSolid.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskContainer {
    private static TaskContainer obj;
    private final Map<String, List<Task>> tasks;

    private TaskContainer() {
        tasks = new LinkedHashMap<>();
    }

    public static synchronized Map<String, List<Task>> getTasks() {
        if (obj == null)
            obj = new TaskContainer();
        return obj.tasks;
    }
}
