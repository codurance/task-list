package com.codurance.training.tasks.domain;

public final class Task {
    public static final boolean DONE = true;
    public static final boolean NOT_DONE = false;

    public static final boolean FOUND_TASK_AND_MARKED_AS_DONE = true;
    public static final boolean TASK_NOT_FOUND = false;

    private static int lastId;

    private final long id;
    private final String description;
    private boolean done;

    public Task(String description) {
        this.id = nextTaskId();
        this.description = description;
        this.done = NOT_DONE;
    }

    public static long nextTaskId() {
        return ++lastId;
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

    public boolean setDone(int id, boolean done) {
        if (this.id == id) {
            setDone(done);
            return FOUND_TASK_AND_MARKED_AS_DONE;
        }
        return TASK_NOT_FOUND;
    }
}
