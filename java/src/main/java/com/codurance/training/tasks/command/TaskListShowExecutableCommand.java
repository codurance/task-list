package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.ProjectsToTasks;

public class TaskListShowExecutableCommand implements ExecutableCommand {
    private final ProjectsToTasks projectsToTasks;

    public TaskListShowExecutableCommand(ProjectsToTasks projectsToTasks) {
        this.projectsToTasks = projectsToTasks;
    }

    @Override
    public void execute(CommandLine commandLine) {
        projectsToTasks.show();
    }
}