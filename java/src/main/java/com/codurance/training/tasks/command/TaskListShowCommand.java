package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.ProjectsToTasks;

public class TaskListShowCommand extends Command {
    private final ProjectsToTasks projectsToTasks;

    public TaskListShowCommand(String name, ProjectsToTasks projectsToTasks) {
        super(name);
        this.projectsToTasks = projectsToTasks;
    }

    @Override
    public void execute(CommandLine... subCommandLine) {
        projectsToTasks.show();
    }
}
