package com.codurance.training.tasks.interfaces;

import java.util.Date;

public interface AddTask {
    void addTask(String taskId, String projectId, String description, Date deadline);
}
