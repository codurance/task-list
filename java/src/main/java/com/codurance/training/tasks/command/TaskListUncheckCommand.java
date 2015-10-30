package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.ProjectsToTasks;

import static com.codurance.training.tasks.domain.Task.NOT_DONE;

public class TaskListUnCheckCommand implements Command {

    private final ProjectsToTasks projectsToTasks;

    public TaskListUnCheckCommand(ProjectsToTasks projectsToTasks) {
        this.projectsToTasks = projectsToTasks;
    }

    @Override
    public void execute(CommandLine commandLine) {
        String idString = commandLine.getFirstParameter();
        projectsToTasks.setDone(idString, NOT_DONE);
    }
}
