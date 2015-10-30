package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.io.Screen;

public class TaskListAddCommand implements Command {
    private Screen screen;
    private final ProjectsToTasks projectsToTasks;

    public TaskListAddCommand(Screen screen, ProjectsToTasks projectsToTasks) {
        this.screen = screen;
        this.projectsToTasks = projectsToTasks;
    }

    @Override
    public void execute(CommandLine commandLine) {
        CommandLine subCommandLine = new CommandLine(commandLine.getRestOfParameters(), screen, projectsToTasks);

        Command command = subCommandLine.getCommand();
        command.execute(subCommandLine);
    }
}