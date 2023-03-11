package com.codurance.training.task;

import java.util.Date;

public interface TaskInterface {

    String getId();

    String getDescription();

    boolean isDone();

    void setId(String idString);

    void setDone(boolean done);

    void setDeadline(Date deadline);

    Date getDeadline();

    Date getCreatedDate();
}
