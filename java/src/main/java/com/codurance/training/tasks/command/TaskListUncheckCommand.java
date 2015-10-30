package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.ProjectsToTasks;

import static com.codurance.training.tasks.domain.Task.NOT_DONE;

public class TaskListUnCheckCommand extends Command {

    private final ProjectsToTasks projectsToTasks;

    public TaskListUnCheckCommand(String name, ProjectsToTasks projectsToTasks) {
        super(name);
        this.projectsToTasks = projectsToTasks;
    }

    public void unCheck(CommandLine commandLine) {
        String idString = commandLine.getFirstParameter();
        projectsToTasks.setDone(idString, NOT_DONE);
    }
}
