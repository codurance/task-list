package com.codurance.training.services.ServicesImpl;

import com.codurance.training.services.*;
import com.codurance.training.tasks.Task;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ExecuteImpl implements Execute {

        private Map<String, List<Task>> tasks;
        private BufferedReader in;
        private PrintWriter out;
        private Add addingService;
        private Check checkService;
        private Show showService;
        private Help helpService;
        private DueTasks dueTasksToday;
        private Delete deletingService;
        private TaskView taskViewServiceByDate;
        private TaskView taskViewServiceByDeadline;
        private TaskView taskViewServiceByProject;

        public ExecuteImpl(Map<String, List<Task>> tasks, BufferedReader in, PrintWriter out) {
            this.tasks = tasks;
            this.in = in;
            this.out = out;
            addingService = new AddCommandImpl(tasks,this.out);
            checkService = new CheckCommandImpl(tasks,this.out);
            showService = new ShowCommandImpl(tasks, this.out);
            helpService = new HelpCommandImpl(this.out);
            dueTasksToday = new DueTasksImpl(tasks, this.out);
            deletingService = new DeleteImpl(tasks, this.out);
            taskViewServiceByDate = new TaskViewByDate(tasks, this.out);
            taskViewServiceByDeadline = new TaskViewByDeadline(tasks, this.out);
            taskViewServiceByProject = new TaskViewByProject(tasks, this.out);

        }

        @Override
        public void execute(String commandLine) {
            String[] commandRest = commandLine.split(" ", 2);
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
                    checkService.unCheck(commandRest[1]);
                    break;
                case "today":
                    dueTasksToday.showTasksDueToday();
                    break;
                case "delete":
                    deletingService.deleteTask(commandRest[1]);
                    break;
                case "view":
                    String viewBy = commandRest[1];
                    switch (viewBy){
                        case "byDate":
                            taskViewServiceByDate.view();
                            break;
                        case "byDeadLine":
                            taskViewServiceByDeadline.view();
                            break;
                        case "byProject":
                            taskViewServiceByProject.view();
                            break;
                    }
                    break;
                case "help":
                    helpService.help();
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

