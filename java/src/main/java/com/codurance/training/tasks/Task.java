package com.codurance.training.tasks;

import java.util.Date;

public final class Task {
    private final long id;
    private final String description;
    private boolean done;

    private Date deadLine;

    public Task(long id, String description, boolean done,Date deadLine) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadLine = deadLine;
    }
    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDeadline() {
        return deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDeadline(Date deadline) {
        this.deadLine = deadline;
    }

    @Override
    public String toString() {
        return "   "+
                id +
                ": " + description +
                ", " + deadLine ;
    }
}
