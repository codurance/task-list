package com.codurance.training.tasks;

import java.util.Date;

class TaskImpl implements Task {
    private final long id;
    private final String description;
    private boolean done;
    private Date deadline;

    public TaskImpl(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public Date getDeadline() {
        return deadline;
    }

    @Override
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
