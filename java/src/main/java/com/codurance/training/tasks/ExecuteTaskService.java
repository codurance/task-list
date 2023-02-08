package com.codurance.training.tasks;

import java.io.*;
import java.util.*;

public class ExecuteTask {
    private final TaskUtil taskUtil;
    Map<String, List<Task>> tasks;
    PrintWriter out;
    Long lastId;

   public ExecuteTask(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
        taskUtil = new TaskUtil(tasks, out);
   }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskUtil.show();
                break;
            case "add":
                taskUtil.add(commandRest[1]);
                break;
            case "check":
                taskUtil.check(commandRest[1]);
                break;
            case "uncheck":
                taskUtil.uncheck(commandRest[1]);
                break;
            case "help":
                taskUtil.help();
                break;
            default:
                taskUtil.error(command);
                break;
        }
    }

}
