package com.codurance.training.tasks.service.impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskConstant;
import com.codurance.training.tasks.TaskData;
import com.codurance.training.tasks.TaskUtil;
import com.codurance.training.tasks.service.TaskInfoService;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TaskInfoServiceImpl extends TaskData implements TaskInfoService {
    private TaskUtil taskUtil = new TaskUtil();
    public void show(PrintWriter out) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                taskUtil.printTask(task);
            }
            out.println();
        }
    }

    public void showDueToday(PrintWriter out) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()){
            out.println(project.getKey());
            for(Task task : project.getValue()){
                if(task.getDeadline().equals(new Date(System.currentTimeMillis()))){
                    taskUtil.printTask(task);
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
        out.println("  delete <ID>");
        out.println("  view by deadline");
        out.println("  view by project");
        out.println();
    }

    public void error(PrintWriter out, String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    public void view(PrintWriter out, String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[1];

        switch (subcommand){
            case TaskConstant.DATE:
                taskUtil.viewByDate();
                break;
            case TaskConstant.DEADLINE:
                taskUtil.viewByDeadline();
                break;
            case TaskConstant.PROJECT:
                taskUtil.viewByProject();
                break;
            default:
                break;
        }
    }
}
