package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Execute {
    public void execute(String commandLine, PrintWriter out, Map<String, List<Task>> tasks) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
            case "view-by-project":
                new Show().show(tasks,out);
                break;
            case "add":
                new Add().add(commandRest[1],tasks,out);
                break;
            case "check":
                new Check().check(tasks,commandRest[1],out);
                break;
            case "uncheck":
                new Uncheck().unCheck(tasks,commandRest[1],out);
                break;
            case "help":
                new Help().help(out);
                break;
            case "delete":
                new AddDelete().delete(tasks,commandRest[1],out);
                break;
            case "deadline":
                new AddDeadline().addDeadLine(tasks,commandRest[1],out);
                break;
            case "today":
                new Today().show(tasks,out);
                break;
            case "view-by-deadline":
            case "view-by-date":
                new ViewByDeadline().show(tasks,out);
                break;
            default:
                new ErrorHandler().error(command,out);
                break;
        }
    }
}
