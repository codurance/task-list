package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.ProjectsToTasks;

public class TaskListShowCommand implements Command {
    private final ProjectsToTasks projectsToTasks;

    public TaskListShowCommand(ProjectsToTasks projectsToTasks) {
        this.projectsToTasks = projectsToTasks;
    }

    @Override
    public void execute(CommandLine commandLine) {
        projectsToTasks.show();
    }
}
