package com.codurance.training.operations.delete;

import com.codurance.training.task.Task;

import java.util.List;
import java.util.Map;

public interface DeleteIdInterface {
    void delete(String id, Map<String, List<Task>> tasks);
}
