package com.codurance.training.tasks;

public class ExecuteCommand {
    public void execute(String commandLine,TaskList taskList) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                taskList.show();
                break;
            case "add":
                taskList.add(commandRest[1]);
                break;
            case "check":
                taskList.check(commandRest[1]);
                break;
            case "uncheck":
                taskList.uncheck(commandRest[1]);
                break;
            case "deadline":
                taskList.setDeadline(commandRest[1]);
                break;
            case "today":
                taskList.showTodayDeadLineTasks();
                break;
            case "help":
                taskList.help();
                break;
            default:
                taskList.error(command);
                break;
        }
    }

}
