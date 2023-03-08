package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";
    private Map<String, Project> addedProjects = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
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
            case "show":
                show();
                break;
            case "add":
                String[] subcommandRest = commandRest[1].split(" ", 2);
                String subcommand = subcommandRest[0];
                //Allow users to delete tasks with the delete <ID> command.
//                View tasks by date with the view by date command.
//                View tasks by deadline with the view by deadline command.
//                Don’t remove the functionality that allows users to view tasks by project, but change the command to view by project.

                if (subcommand.equals("project")) {
                    addedProjects.put(subcommandRest[1], new Project(subcommandRest[1]));
                } else if (subcommand.equals("task")) {
                    String[] projectTask = subcommandRest[1].split(" ", 2);
                    if (addedProjects.get(projectTask[0]) == null) {
                        System.out.printf("Could not find a project with the name \"%s\".", projectTask[0]);
                        System.out.println();
                    }else {
                        Project project = addedProjects.get(projectTask[0]);
                        project.getTasks().add(new Task( (long)(Math.random()*100), projectTask[1], false));
                    }
                }
                break;
            case "check":
            case "uncheck":
                FindTaskById findTaskById =  new FindTaskById(addedProjects);
                Task task = findTaskById.getTask(commandRest[1]) ;
                if(task != null) {
                    CheckUnCheckTask checkTask = new CheckUnCheckTask(task);
                    if(command.equals("check"))
                        checkTask.setDoneUndone(true);
                    else
                        checkTask.setDoneUndone(false);
                }else{
                    System.out.printf("Could not find a task with an ID of %d.", commandRest[1]);
                    System.out.println();
                }
                break;
            case "deadline":
                String[] cmd2 = commandRest[1].split(" ", 2);
                FindTaskById taskById =  new FindTaskById(addedProjects);

                Task task1 = taskById.getTask(cmd2[0]) ;
                try {
                    Date deadLineDate = new SimpleDateFormat("dd-MM-yyyy").parse(cmd2[1]);
                    task1.setDeadline(deadLineDate);
                }catch(ParseException e){
                    System.out.println(e.toString());
                }
                break;
            case "today":
                DueTask dueTask =  new DueTask(addedProjects);
                List<Task> tasks = dueTask.getTaskDueToday();
                if(!tasks.isEmpty()){
                    for(Task t: tasks){
                        System.out.println(t.getDescription());
                    }
                }else{
                    System.out.println("No Task due today");
                }
                break;
            case "delete":
                FindTaskById findTask =  new FindTaskById(addedProjects);
                Project projectTaskToDel = findTask.getProject(commandRest[1]);
                System.out.println(projectTaskToDel.getProjectName());
                if(projectTaskToDel != null){
                    DeleteTask deleteTask = new DeleteTask(projectTaskToDel);
                    deleteTask.deleteTaskFromProject(commandRest[1]);
                }else{
                    System.out.println("No project found with this task.");
                }
                break;
            case "view by deadline":
                try {
                    Date deadLineDate = new SimpleDateFormat("dd-MM-yyyy").parse(commandRest[1]);
                    TaskByDeadline deadLineTask =  new TaskByDeadline(addedProjects);
                    List<Task> tasks_deadline = deadLineTask.getTaskByDeadLine(deadLineDate);
                    if(!tasks_deadline.isEmpty()){
                        for(Task t: tasks_deadline){
                            System.out.println(t.getDescription());
                        }
                    }else{
                        System.out.println("No Task found");
                    }
                }catch(ParseException e){
                    System.out.println(e.toString());
                }
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void show() {
        for (Map.Entry<String, Project> project : addedProjects.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue().getTasks()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
