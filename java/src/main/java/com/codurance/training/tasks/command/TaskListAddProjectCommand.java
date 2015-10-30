package com.codurance.training.tasks.command;

import com.codurance.training.tasks.domain.Project;
import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.domain.Tasks;

public class TaskListAddProjectCommand extends Command {

    private final ProjectsToTasks projectsToTasks;

    public TaskListAddProjectCommand(String name, ProjectsToTasks projectsToTasks) {
        super(name);
        this.projectsToTasks = projectsToTasks;
    }


    public void execute(CommandLine subCommandLine) {
        addProject(new Project(subCommandLine.getFirstParameter()));
    }

    private void addProject(Project project) {
        projectsToTasks.add(project, new Tasks());
    }
}
