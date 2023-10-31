package com.codurance.training.tasks.task;

public interface ITask {
    long getId();
    String getDescription();
    boolean isDone();
    void setDone(boolean done);
}