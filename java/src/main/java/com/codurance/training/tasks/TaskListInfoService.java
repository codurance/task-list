package com.codurance.training.tasks;

public interface TaskListInfoService {
    void show() ;

    void showDueTasksForToday(String commandLine);

    void viewTasksByDeadline();
}
