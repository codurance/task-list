package com.codurance.training.executioner.impl;

import com.codurance.training.actions.impl.TaskActionsImpl;
import com.codurance.training.executioner.TaskExecutioner;

public class TaskExecutionerImpl implements TaskExecutioner {
    TaskActionsImpl taskActions = new TaskActionsImpl();
    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskActions.show();
                break;
            case "add":
                taskActions.add(commandRest[1]);
                break;
            case "check":
                taskActions.check(commandRest[1]);
                break;
            case "uncheck":
                taskActions.uncheck(commandRest[1]);
                break;
            case "help":
                taskActions.help();
                break;
            default:
                taskActions.error(command);
                break;
        }
    }
}
