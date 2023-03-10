package com.codurance.training.tasks;

import java.util.Date;

public final class Task {
    private final String description;
    private final String uniqueId;
    private final long projectId;
    private boolean done;
    private final Date date;

    private Date deadline;

    public Task(String uniqueId, long projectId, String description, boolean done) {
        this.uniqueId = uniqueId;
        this.projectId = projectId;
        this.description = description;
        this.done = done;
        this.date = new Date();
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

    public void setDeadline(Date date) {
        this.deadline = date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Date getDate() {
        return date;
    }

    public long getProjectId() {
        return projectId;
    }
}
