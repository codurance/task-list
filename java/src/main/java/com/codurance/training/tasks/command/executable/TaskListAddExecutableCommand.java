package com.codurance.training.tasks.command.executable;

import com.codurance.training.tasks.command.CommandLine;
import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.io.Screen;

public class TaskListAddExecutableCommand implements ExecutableCommand {
    private Screen screen;
    private final ProjectsToTasks projectsToTasks;

    public TaskListAddExecutableCommand(Screen screen, ProjectsToTasks projectsToTasks) {
        this.screen = screen;
        this.projectsToTasks = projectsToTasks;
    }

    @Override
    public void execute(CommandLine commandLine) {
        CommandLine subCommandLine = new CommandLine(commandLine.getRestOfParameters(), screen, projectsToTasks);

        ExecutableCommand executableCommand = (ExecutableCommand) subCommandLine.getCommand();
        executableCommand.execute(subCommandLine);
    }
}