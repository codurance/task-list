package com.codurance.training.tasks.service;

import java.text.ParseException;
import java.util.Date;

public interface TaskListUpdateService extends TaskListService {
    void check(String idString);
    void uncheck(String idString);
    void setDone(String idString, Boolean done);
    void setDeadline(String commandLine) throws ParseException;
}
