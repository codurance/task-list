package com.codurance.training.tasksSolid;

import com.codurance.training.tasksSolid.commands.*;

import java.io.PrintWriter;

public class TaskExecutor {

    private final Add addCommand;
    private final AddTask addTaskCommand;
    private final ShowTask showTaskCommand;
    private final UpdateTask updateTaskCommand;
    private final Help helpCommand;
    private final PrintWriter out;

    public TaskExecutor(PrintWriter out) {
        addTaskCommand = new AddTask(out);
        addCommand = new Add(addTaskCommand);
        showTaskCommand = new ShowTask(out);
        updateTaskCommand = new UpdateTask(out);
        helpCommand = new Help(out);
        this.out = out;
    }

    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                showTaskCommand.printAllTaskDetails();
                break;
            case "add":
                addCommand.addTaskOrProject(commandRest[1]);
                break;
            case "check":
                updateTaskCommand.check(commandRest[1]);
                break;
            case "uncheck":
                updateTaskCommand.uncheck(commandRest[1]);
                break;
            case "help":
                helpCommand.printAvailableCommands();
                break;
            default:
                error(command);
                break;
        }
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
