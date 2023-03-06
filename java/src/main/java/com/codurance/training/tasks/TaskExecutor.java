package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TaskExecutor implements TaskExecutorService{


    private TaskList taskList;
    private TaskListInfoService taskInfoService;
    private TaskListAddService taskListAddService;
    private CheckAndUncheckService checkAndUncheckService;
    private ErrorService errorService;
    private HelperService helperService;

    private DeadlineService deadlineService;

    private final TaskDeleteService taskDeleteService;

    private BufferedReader in;
    public PrintWriter out;



    public TaskExecutor( BufferedReader in, PrintWriter out, Map<String, List<Task>> tasks) {

        this.out = out;
        this.taskInfoService = new TaskListInfoServiceImpl(out, tasks);
        this.taskListAddService = new TaskListAddServiceImpl(out, tasks);
        this.checkAndUncheckService = new CheckAndUncheckServiceImpl(in, out,tasks);
        this.errorService = new ErrorServiceImpl(out,tasks);
        this.helperService = new HelperServiceImpl(out);
        this.deadlineService = new DeadlineServiceImpl(out,tasks);
        this.taskDeleteService = new TaskDeleteServiceImpl(out, tasks);

    }
    @Override
    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
            case "viewByProject":
                taskInfoService.show();
                break;
            case "add":
                taskListAddService.add(commandRest[1]);
                break;
            case "check":
                checkAndUncheckService.check(commandRest[1]);
                break;
            case "uncheck":
                checkAndUncheckService.uncheck(commandRest[1]);
                break;
            case "help":
                helperService.help();
                break;
            case "addDeadline":
                deadlineService.addDeadline(commandLine);
                break;
            case "delete":
                taskDeleteService.deleteTask(commandRest[1]);
                break;
            case "viewbydeadline":
            case "viewbydate":
                taskInfoService.viewTasksByDeadline();
                break;
            default:
                errorService.error(command);
                break;
        }
    }


}
