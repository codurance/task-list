package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();

    private final AddProjectImp addProjectImp;
    private final UncheckTaskImp uncheckTaskImp;
    private final CheckTaskImp checkTaskImp;

    private final AddTaskImp addTaskImp;
    private final TaskView taskView;
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.uncheckTaskImp = new UncheckTaskImp();
        this.checkTaskImp = new CheckTaskImp();
        this.addProjectImp = new AddProjectImp(tasks);
        this.addTaskImp = new AddTaskImp(tasks);
        this.taskView = new TaskView();
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                setDone(commandRest[1], checkTaskImp);
                break;
            case "uncheck":
                setDone(commandRest[1], uncheckTaskImp);
                break;
            case "help":
                help();
                break;
            case "deadline":
                setDeadLine(commandRest[1]);
                break;
            case "today":
                showTodayTasks();
                break;
            case "delete":
                delete(commandRest[1]);
                break;
            case "view":
                viewTasks(commandRest[1]);
                break;
            default:
                error(command);
                break;
        }
    }


    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProjectImp.add(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            addTaskImp.add(subcommandRest[1]);
        }
    }

    private void setDone(String idString, TaskActionService taskActionService) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().equals(idString)) {
                    taskActionService.action(task);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.", idString);
        out.println();
    }

    private void help() {
        out.println("Commands:");
        out.println("  view by project");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description> <task id>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  view by date <date>");
        out.println("  view by deadline");
        out.println("  today");
        out.println("  delete <task_id>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    private void setDeadLine(String command){
        String[] subcommandRest = command.split(" ", 2);
        String taskId = subcommandRest[0];
        String deadLine = subcommandRest[1];

        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().equals(taskId)) {
                    task.setDeadline(LocalDate.parse(deadLine));
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.%n", taskId);
    }

    private void showTodayTasks(){
        LocalDate currentDate = LocalDate.now();

        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                LocalDate deadline = task.getDeadline();
                if (deadline != null && deadline.equals(currentDate)) {
                    out.printf("    [%c] %s: %s : %s%n", (task.isDone() ? 'x' : ' '), task.getId(),
                            task.getDescription(), deadline);
                }
            }
            out.println();
        }
    }

    public void delete(String strId) {
        for (List<Task> projectTasks : tasks.values()) {
            for (Task task : projectTasks) {
                if (task.getId().equals(strId)) {
                    projectTasks.remove(task);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.%n", strId);
    }

    public void viewTasks(String commandLine){
        String[] subcommandRest = commandLine.split(" ", 3);
        String subcommand = subcommandRest[1];
        if(subcommand.equals("date")){
            viewTasksBydate(subcommandRest[2]);
        }else if(subcommand.equals("project")){
            taskView.showTasks(tasks);
        }
        else {
            viewTasksByDeadLine();
        }
    }

    private void viewTasksBydate(String date) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getDeadline() != null && task.getDeadline().toString().equals(date)) {
                    out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                }
            }
            out.println();
        }
    }

    public void viewTasksByDeadLine(){
        List<Task> allTasks = new ArrayList<>();
        for (List<Task> tasks : this.tasks.values()) {
            allTasks.addAll(tasks);
        }
        allTasks.sort(Comparator.comparing(Task::getDeadline));
        for (Task task : allTasks) {
            if (task.getDeadline() != null) {
                out.printf("%s: %s : %s%n", task.getId(),task.getDescription(), task.getDeadline());
            }
        }
    }
}