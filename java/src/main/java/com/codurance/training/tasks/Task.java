package com.codurance.training.tasks;

import java.util.Date;

public final class Task {
    private final String id;
    private final String description;
    private boolean done;

    private Date createdDate;

    private Date deadLine;

    public Task(String id, String description, boolean done, Date createdDate, Date deadLine) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.createdDate = createdDate;
        this.deadLine = deadLine;
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


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }


}