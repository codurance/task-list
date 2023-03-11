package com.codurance.training.operations.delete;

import com.codurance.training.task.Task;

import java.util.List;
import java.util.Map;

public class DeleteId implements DeleteIdInterface{
    @Override
    public void delete(String idString, Map<String, List<Task>> tasks) {
        tasks.forEach((project, tasksList) -> {
            tasksList.removeIf(task -> task.getId().equals(idString));
        });
    }
}
