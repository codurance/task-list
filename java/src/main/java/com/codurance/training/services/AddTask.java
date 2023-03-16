package com.codurance.training.services;

import java.util.Date;

public interface AddTask {
    void addTask(String taskId, String project, String description, Date deadLine);
}
