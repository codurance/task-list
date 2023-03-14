package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class TaskView implements  TaskViewService{

    @Override
    public void showTasks(Map<String, List<Task>> tasks) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s : %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getDeadline() != null ? task.getDeadline().toString() : "None");
            }
            out.println();
        }
    }
}
