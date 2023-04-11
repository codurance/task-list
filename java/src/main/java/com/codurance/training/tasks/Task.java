package com.codurance.training.tasks;

import java.util.Date;

public final class Task {
    private final String id;
    private Date deadline;
    private final Date createdAt;

    public Date getDeadline() {
        return deadline;
    }

    public Task setDeadline(Date deadline) {
        this.deadline = deadline;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    private final String description;
    private boolean done;

    public Task(String id, String description, boolean done, Date createdAt, Date deadline) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.createdAt = createdAt;
        this.deadline = deadline;
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

    public void setDone(boolean done) {
        this.done = done;
    }
}
