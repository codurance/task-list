package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.Project;
import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.domain.Tasks;

public class TaskListAddProjectExecutableCommand implements ExecutableCommand {

    private final ProjectsToTasks projectsToTasks;

    public TaskListAddProjectExecutableCommand(ProjectsToTasks projectsToTasks) {
        this.projectsToTasks = projectsToTasks;
    }

    public void execute(CommandLine commandLine) {
        Project project = new Project(commandLine.getFirstParameter());
        projectsToTasks.add(project, new Tasks());
    }
}