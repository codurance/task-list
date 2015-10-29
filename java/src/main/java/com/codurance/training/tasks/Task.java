package com.codurance.training.tasks;

public final class Task {
    public static final boolean NOT_DONE = false;

    public static final boolean FOUND_TASK_AND_MARKED_AS_DONE = true;
    public static final boolean TASK_NOT_FOUND = false;

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

    public boolean setDoneUsing(int id, boolean done) {
        if (this.id == id) {
            setDone(done);
            return FOUND_TASK_AND_MARKED_AS_DONE;
        }
        return TASK_NOT_FOUND;
    }
}
