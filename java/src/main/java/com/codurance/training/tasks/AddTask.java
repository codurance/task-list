package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class AddTask {
    public void addTask(String project, String description, Map<String, List<Task>> tasks) {
        String[] subcommand = description.split(" ");
        List<Task> projectTasks = tasks.get(project);

        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        if(!Pattern.matches("^(0|[1-9][0-9]*)$", subcommand[1])){
            out.printf("Task Id should match this pattern \"no special characters and spaces\".");
            out.println();
        }
        else{
            projectTasks.add(new Task(Integer.parseInt(subcommand[1]), subcommand[0], false));
        }
    }
}
