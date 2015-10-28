package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Tasks {
    public static final Tasks NO_TASKS = null;

    private static final String PRINT_LINE_FORMATTER = "    [%c] %d: %s%n";

    private static final char TASK_DONE_SYMBOL = 'x';
    private static final char TASK_NOT_DONE_SYMBOL = ' ';

    private List<Task> tasksList = new ArrayList<>();

    public void add(Task task) {
        tasksList.add(task);
    }

    public boolean setDone(int id, boolean done) {
        for (Task task : tasksList) {
            if (task.getId() == id) {
                task.setDone(done);
                return true;
            }
        }
        return false;
    }

    public void show(PrintWriter out) {
        for (Task task : tasksList) {
            out.printf(PRINT_LINE_FORMATTER, (
                            task.isDone()
                                    ? TASK_DONE_SYMBOL
                                    : TASK_NOT_DONE_SYMBOL),
                    task.getId(), task.getDescription());
        }
    }
}
