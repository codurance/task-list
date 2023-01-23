package com.codurance.training.tasksSolid.manager;

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
        CommandEnum command = CommandEnum.getCommandEnum(commandRest[0]);
        switch (command) {
            case SHOW:
                showTaskCommand.printAllTaskDetails();
                break;
            case ADD:
                addCommand.addTaskOrProject(commandRest[1]);
                break;
            case CHECK:
                updateTaskCommand.check(commandRest[1]);
                break;
            case UNCHECK:
                updateTaskCommand.uncheck(commandRest[1]);
                break;
            case HELP:
                helpCommand.printAvailableCommands();
                break;
            case INVALID:
                error(commandRest[0]);
                break;
            default:
                break;
        }
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
