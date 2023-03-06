package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Add {
        public void add(String commandLine, Map<String, List<Task>> tasks, PrintWriter out) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            new AddProject().add(tasks,subcommandRest[1],out);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            new AddTask().addTask(projectTask[0], projectTask[1],tasks);
        }
        else{
            new ErrorHandler().error(subcommand,out);
        }
    }
}
