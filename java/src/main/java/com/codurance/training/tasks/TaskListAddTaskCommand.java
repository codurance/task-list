package com.codurance.training.tasks;

import com.codurance.training.tasks.command.Command;
import com.codurance.training.tasks.command.CommandLine;
import com.codurance.training.tasks.domain.Project;
import com.codurance.training.tasks.domain.ProjectsToTasks;

public class TaskListAddTaskCommand extends Command{
    private final ProjectsToTasks projectsToTasks;

    public TaskListAddTaskCommand(String name, ProjectsToTasks projectsToTasks) {
        super(name);
        this.projectsToTasks = projectsToTasks;
    }

    public void execute(CommandLine subCommandLine) {
        addTaskToProject(subCommandLine);
    }

    private void addTaskToProject(CommandLine commandLine) {
        CommandLine subCommandLine = new CommandLine(commandLine.getRestOfParameters(), projectsToTasks);

        String projectName = commandLine.getFirstParameter();
        Project project = new Project(projectName);
        String taskDescription = subCommandLine.getRestOfParameters();

        projectsToTasks.addTask(project, taskDescription);
    }
}
