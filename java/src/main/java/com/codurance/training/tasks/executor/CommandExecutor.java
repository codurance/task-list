package com.codurance.training.tasks.executor;

import com.codurance.training.tasks.task.ITask;
import com.codurance.training.tasks.tasklist.ITaskList;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public class CommandExecutor implements ICommandExecutor {
    private final BufferedReader in;
    private final PrintWriter out;
    private final ITaskList taskList;

    public CommandExecutor(BufferedReader reader, PrintWriter writer, ITaskList taskList) {
        this.in = reader;
        this.out = writer;
        this.taskList = taskList;
    }

    public void executeCommand(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                taskList.check(commandRest[1]);
                break;
            case "uncheck":
                taskList.uncheck(commandRest[1]);
                break;
            default:
                error(command);
                break;
        }
    }

    private void show() {
        Map<String, List<ITask>> tasks = taskList.getTasks();
        for (Map.Entry<String, List<ITask>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (ITask task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }


    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            taskList.addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            taskList.addTask(projectTask[0], projectTask[1]);
        }
    }

    private void error(String command) {
        out.printf("Error: \"%s\" is not a valid command.%n", command);
        out.println();
    }
}
