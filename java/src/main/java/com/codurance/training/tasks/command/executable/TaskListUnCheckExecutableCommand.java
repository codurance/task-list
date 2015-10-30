package com.codurance.training.tasks.command.executable;

import com.codurance.training.tasks.command.CommandLine;
import com.codurance.training.tasks.domain.ProjectsToTasks;

import static com.codurance.training.tasks.domain.Task.NOT_DONE;

public class TaskListUnCheckExecutableCommand implements ExecutableCommand {

    private final ProjectsToTasks projectsToTasks;

    public TaskListUnCheckExecutableCommand(ProjectsToTasks projectsToTasks) {
        this.projectsToTasks = projectsToTasks;
    }

    @Override
    public void execute(CommandLine commandLine) {
        String idString = commandLine.getFirstParameter();
        projectsToTasks.setDone(idString, NOT_DONE);
    }
}