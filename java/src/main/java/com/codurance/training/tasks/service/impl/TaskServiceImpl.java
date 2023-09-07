package com.codurance.training.tasks.service.impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskConstant;
import com.codurance.training.tasks.TaskData;
import com.codurance.training.tasks.service.TaskService;
import com.codurance.training.tasks.TaskUtil;

import java.io.PrintWriter;
import java.util.*;

public class TaskServiceImpl extends TaskData implements TaskService {

    private TaskUtil taskUtil = new TaskUtil();
    public void add(PrintWriter out, String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals(TaskConstant.PROJECT)) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals(TaskConstant.TASK)) {

            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1], out);
        }
    }

    public void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    public void addTask(String project, String description, PrintWriter out) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(taskUtil.nextId(), description, false));
    }

    public void check(PrintWriter out, String idString) {
        taskUtil.setDone(idString, true);
    }

    public void uncheck(PrintWriter out, String idString) {
        taskUtil.setDone(idString, false);
    }


    public void addDeadline(PrintWriter out, String commandLine){
        String[] subcommandRest = commandLine.split(" ", 2);
        int id = Integer.parseInt(subcommandRest[0]);
        Date deadline = new Date(subcommandRest[1]);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDeadline(deadline);
                    return;
                }
            }
        }
    }

    public void delete(PrintWriter out, String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        int id = Integer.parseInt(subcommandRest[0]);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task = null;
                    return;
                }
            }
        }
    }

}
