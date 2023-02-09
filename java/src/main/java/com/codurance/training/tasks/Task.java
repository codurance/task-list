package com.codurance.training.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Task {
    private final Long id;
    private final String description;
    private Boolean done;
    private Date deadline;

    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.deadline = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline){
        this.deadline = deadline;
    }
}
