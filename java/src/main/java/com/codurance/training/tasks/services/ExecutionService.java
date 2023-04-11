package com.codurance.training.tasks.services;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.interfaces.*;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.*;

public class ExecutionService implements ExecuteProject {
    private Map<String, List<Task>> tasks;
    private BufferedReader in;
    private PrintWriter out;
    private AddService addingService;
    private CheckTask checkService;
    private ShowProject showService;
    private IHelpService helpService;
    private TodayTasks todayDueTasks;
    private DeleteTask deletingService;
    private ViewProject taskViewServiceByDate;
    private ViewProject taskViewServiceByDeadline;
    private ViewProject taskViewServiceByProject;

    public ExecutionService(Map<String, List<Task>> tasks, BufferedReader in, PrintWriter out) {
        this.tasks = tasks;
        this.in = in;
        this.out = out;
        addingService = new AddServiceImpl(tasks, this.out);
        checkService = new CheckService(tasks, this.out);
        showService = new ShowService(tasks, this.out);
        helpService = new HelpService(this.out);
        todayDueTasks = new TodayTaskImpl(tasks, this.out);
        deletingService = new DeleteTaskService(tasks, this.out);
        taskViewServiceByDate = new TaskViewByDate(tasks, this.out);
        taskViewServiceByDeadline = new TaskViewByDeadline(tasks, this.out);
        taskViewServiceByProject = new TaskViewByProject(tasks, this.out);
    }

    @Override
    public void execute(String commandValue) {
        String[] commandRest = commandValue.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                showService.show();
                break;
            case "add":
                addingService.add(commandRest[1]);
                break;
            case "check":
                checkService.check(commandRest[1]);
                break;
            case "uncheck":
                checkService.uncheck(commandRest[1]);
                break;
            case "today":
                todayDueTasks.displayTodayDueTasks();
                break;
            case "delete":
                deletingService.deleteTask(commandRest[1]);
                break;
            case "view":
                String viewBy = commandRest[1];
                switch (viewBy) {
                    case "byDate":
                        taskViewServiceByDate.view();
                        break;
                    case "byDeadline":
                        taskViewServiceByDeadline.view();
                        break;
                    case "byProject":
                        taskViewServiceByProject.view();
                        break;
                }
                break;
            case "help":
                helpService.getHelp();
                break;
            default:
                out.printf("Invalid command provided as => ", command);
                out.println();
                break;
        }
    }

}
