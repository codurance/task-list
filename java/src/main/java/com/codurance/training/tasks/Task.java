package com.codurance.training.tasks;

import java.util.Date;

interface Task {
    long getId();

    String getDescription();

    boolean isDone();

    void setDone(boolean done);

    Date getDeadline();

    void setDeadline(Date deadline);
}