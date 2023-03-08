package com.codurance.training.tasks;

import java.util.Map;

public class CheckUnCheckTask {
    Task task;

    public CheckUnCheckTask(Task task) {
        this.task = task;
    }

    public void setDoneUndone(boolean done){
        task.setDone(done);
    }
}
