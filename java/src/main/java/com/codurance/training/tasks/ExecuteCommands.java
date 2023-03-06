package com.codurance.training.tasks;

public class ExecuteCommands {
    public void execute(String commandLine,TaskList taskList) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "view":
                new ShowFactory().get(commandRest[1]).show(taskList.out,taskList.tasks);
                break;
            case "add":
                taskList.add(commandRest[1]);
                break;
            case "check":
                new UpdateTask().setDone(commandRest[1],true,taskList.out,taskList.tasks);
                break;
            case "uncheck":
                new UpdateTask().setDone(commandRest[1],false,taskList.out,taskList.tasks);
                break;
            case "deadline":
                new TaskDeadline().setDeadline(commandRest[1],taskList.out,taskList.tasks);
                break;
            case "today":
                new ShowTodayDeadlineTasks().show(taskList.out,taskList.tasks);
                break;
            case "help":
                new Help().help(taskList.out);
                break;
            default:
                new Error().error(command,taskList.out);
                break;
        }
    }

}
