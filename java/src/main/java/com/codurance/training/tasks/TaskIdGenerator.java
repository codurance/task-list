package com.codurance.training.tasks;

public class TaskIdGenerator implements IdGenerator {
    private long lastId = 0;
    @Override
    public long nextId() {
        return ++lastId;
    }
}
