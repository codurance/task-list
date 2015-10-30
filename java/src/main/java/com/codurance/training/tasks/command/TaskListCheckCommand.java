package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.domain.Task;

public class TaskListCheckCommand implements Command {

    private final ProjectsToTasks projectsToTasks;

    public TaskListCheckCommand(ProjectsToTasks projectsToTasks) {
        this.projectsToTasks = projectsToTasks;
    }

    @Override
    public void execute(CommandLine commandLine) {
        String idString = commandLine.getFirstParameter();
        projectsToTasks.setDone(idString, Task.DONE);
    }
}
