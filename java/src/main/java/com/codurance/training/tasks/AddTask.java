package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskList;

public class AddTask implements AddService{
    private long lastId = 0;
    @Override
    public void CRUD(String commandLine, PrintWriter out) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String[] projectTask = subcommandRest[1].split(" ", 2);
        String project = projectTask[0];
        String description = projectTask[1];
        List<Task> projectTasks = TaskList.tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(++lastId, description, false));
    }
}
