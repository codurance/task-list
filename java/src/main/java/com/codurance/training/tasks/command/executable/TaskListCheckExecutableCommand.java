package com.codurance.training.tasks.command.executable;

import com.codurance.training.tasks.command.CommandLine;
import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.domain.Task;

public class TaskListCheckExecutableCommand implements ExecutableCommand {

    private final ProjectsToTasks projectsToTasks;

    public TaskListCheckExecutableCommand(ProjectsToTasks projectsToTasks) {
        this.projectsToTasks = projectsToTasks;
    }

    @Override
    public void execute(CommandLine commandLine) {
        String idString = commandLine.getFirstParameter();
        projectsToTasks.setDone(idString, Task.DONE);
    }
}