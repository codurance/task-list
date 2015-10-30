package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.domain.Task;

public class TaskListCheckCommand extends Command {

    private final ProjectsToTasks projectsToTasks;

    public TaskListCheckCommand(String name, ProjectsToTasks projectsToTasks) {
        super(name);
        this.projectsToTasks = projectsToTasks;
    }

    public void check(CommandLine commandLine) {
        String idString = commandLine.getFirstParameter();
        projectsToTasks.setDone(idString, Task.DONE);
    }
}
