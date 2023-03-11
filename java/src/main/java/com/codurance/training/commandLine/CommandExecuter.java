package com.codurance.training.commandLine;

import com.codurance.training.task.TaskList;

import java.io.PrintWriter;

public class CommandExecuter implements CommandExecuterInterface {
    private final TaskList taskList=new TaskList();
    public void execute(PrintWriter out, String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "view":
                taskList.showTaskList(out,commandRest[1]);
                break;
            case "add":
                taskList.add(out,commandRest[1]);
                break;
            case "check":
                taskList.manageTask(out,commandRest[1],true);
                break;
            case "uncheck":
                taskList.manageTask(out,commandRest[1],false);
                break;
            case "help":
                taskList.help(out);
                break;
            case "deadline":
                taskList.manageDeadline(commandRest[1]);
                break;
            case "today":
                taskList.showTodayProjects(out);
                break;
            case "customize":
                taskList.customizeId(out,commandRest[1]);
                break;
            case "delete":
                taskList.deleteId(commandRest[1]);
                break;
            default:
                taskList.error(out,command);
                break;
        }
    }
}

