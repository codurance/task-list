package com.codurance.training.tasks.service.impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskData;
import com.codurance.training.tasks.service.TaskInfoService;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TaskInfoServiceImpl extends TaskData implements TaskInfoService {
    public void show(PrintWriter out) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    public void showDueToday(PrintWriter out) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()){
            out.println(project.getKey());
            for(Task task : project.getValue()){
                if(task.getDeadline().equals(new Date(System.currentTimeMillis()))){
                    out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                }
            }
        }
    }

    public void help(PrintWriter out) {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  deadline <ID> <date>");
        out.println();
    }

    public void error(PrintWriter out, String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
