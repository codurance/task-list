package com.codurance.training.tasks.command.executable;

import com.codurance.training.tasks.command.CommandLine;
import com.codurance.training.tasks.domain.Project;
import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.io.Screen;

public class TaskListAddTaskExecutableCommand implements ExecutableCommand {
    private Screen screen;
    private final ProjectsToTasks projectsToTasks;

    public TaskListAddTaskExecutableCommand(Screen screen, ProjectsToTasks projectsToTasks) {
        this.screen = screen;
        this.projectsToTasks = projectsToTasks;
    }

    public void execute(CommandLine commandLine) {
        addTaskToProject(commandLine);
    }

    private void addTaskToProject(CommandLine commandLine) {
        CommandLine subCommandLine = new CommandLine(commandLine.getRestOfParameters(), screen, projectsToTasks);

        String projectName = commandLine.getFirstParameter();
        Project project = new Project(projectName);
        String taskDescription = subCommandLine.getRestOfParameters();

        projectsToTasks.addTask(project, taskDescription);
    }
}