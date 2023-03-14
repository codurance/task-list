package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

public interface TaskViewService {
    public void showTasks(Map<String, List<Task>> tasks);
}
