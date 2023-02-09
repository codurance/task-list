package com.codurance.training.tasks.service;

import java.text.ParseException;

public interface TaskListViewService {
    void show();
    void listTasksDueToday() throws ParseException;
}
