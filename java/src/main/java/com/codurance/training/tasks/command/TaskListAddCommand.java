package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskListAddTaskCommand;
import com.codurance.training.tasks.domain.ProjectsToTasks;

public class TaskListAddCommand extends Command {
    private final ProjectsToTasks projectsToTasks;

    public TaskListAddCommand(String commandName, ProjectsToTasks projectsToTasks) {
        super(commandName);
        this.projectsToTasks = projectsToTasks;
    }

    public void add(CommandLine commandLine) {
        CommandLine subCommandLine = new CommandLine(commandLine.getRestOfParameters(), projectsToTasks);

        if (SUB_CMD_PROJECT.equals(subCommandLine.getCommand().toString())) {
            TaskListAddProjectCommand taskListAddProjectCommand = new TaskListAddProjectCommand("", projectsToTasks);
            taskListAddProjectCommand.execute(subCommandLine);
        } else if (SUB_CMD_TASK.equals(subCommandLine.getCommand().toString())) {
            TaskListAddTaskCommand taskListAddTaskCommand = new TaskListAddTaskCommand("", projectsToTasks);
            taskListAddTaskCommand.execute(subCommandLine);
        }
    }
}