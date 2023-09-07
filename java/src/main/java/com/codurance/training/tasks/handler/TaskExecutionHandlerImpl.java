package com.codurance.training.tasks.handler;

import com.codurance.training.tasks.actions.TaskActionHandlerImpl;

public class TaskExecutionHandlerImpl implements TaskExecutionHandler{
    private final TaskActionHandlerImpl taskActionHandlerImpl = new TaskActionHandlerImpl();
    public void execute(String commandLine){
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskActionHandlerImpl.show();
                break;
            case "add":
                taskActionHandlerImpl.add(commandRest[1]);
                break;
            case "check":
                taskActionHandlerImpl.check(commandRest[1]);
                break;
            case "uncheck":
                taskActionHandlerImpl.uncheck(commandRest[1]);
                break;
            case "help":
                taskActionHandlerImpl.help();
                break;
            default:
                taskActionHandlerImpl.error(command);
                break;
        }
    }
}
