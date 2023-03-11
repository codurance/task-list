package com.codurance.training.task;

import java.util.Date;

public final class Task implements TaskInterface{
    private String id;
    private final String description;
    private boolean done;
    private final Date createdDate;
    private Date deadline;

    public Task(String id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.createdDate =new Date();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public void setId(String idString) {
        this.id=idString;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDeadline(Date deadline) {
        this.deadline=deadline;
    }

    @Override
    public Date getDeadline() {
        return deadline;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
}
