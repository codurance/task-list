package com.codurance.training.tasks;

import java.util.List;

public class AddTask {
    public void add(String project, String description, TaskList taskList) {
        List<Task> projectTasks = taskList.tasks.get(project);
        if (projectTasks == null) {
            taskList.out.printf("Could not find a project with the name \"%s\".", project);
            taskList.out.println();
            return;
        }
        projectTasks.add(new Task(taskList.nextId(), description, false));
    }
}
