package com.codurance.training.tasks;

public final class Task {
    static final boolean NOT_DONE = false;

    private final long id;
    private final String description;
    private boolean done;

    public Task(long id, String description) {
        this.id = id;
        this.description = description;
        this.done = NOT_DONE;
    }

    public long getId() {
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
