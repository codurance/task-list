package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Map<Long, Project> projectList = new LinkedHashMap<>();
    private final List<String> taskIds = new ArrayList<>();
    private final ViewService viewService = new View();
    private final AddService addProjectService = new AddProject();
    private final AddService addTaskService = new AddTask();
    private final StatusService statusService = new Status();
    private final DeadlineService deadlineService = new Deadline();
    private final DeleteService deleteService = new DeleteTask();

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
            try{
                execute(command);
            } catch(Exception e) {
                out.println(e);
            }
        }
    }

    private void execute(String commandLine) throws Exception {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "view_by_project":
                viewService.viewTasksByProject(projectList, out);
                break;
            case "view_by_date":
                viewService.viewTasksByDate(projectList, out);
                break;
            case "view_by_deadline":
                viewService.viewTasksByDeadline(projectList, out);
                break;
            case "add":
                String[] subcommandRest = commandRest[1].split(" ", 2);
                String subcommand = subcommandRest[0];
                if (subcommand.equals("project")) {
                    addProjectService.add(projectList, subcommandRest[1], out);
                } else if (subcommand.equals("task")) {
                    String[] projectTask = subcommandRest[1].split(" ", 3);
                    if(!taskIds.contains(projectTask[1])) {
                        boolean flag = addTaskService.add(projectList, subcommandRest[1], out);
                        if(flag) taskIds.add(projectTask[1]);
                    } else {
                        out.printf("A task with ID \"%s\" exists already. Use any other id.", projectTask[1]);
                        out.println();
                    }
                }
                break;
            case "check":
                statusService.changeStatus(projectList, commandRest[1], true, out);
                break;
            case "uncheck":
                statusService.changeStatus(projectList, commandRest[1], false, out);
                break;
            case "deadline":
                SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
                String[] subcommandRest1 = commandRest[1].split(" ", 2);
                deadlineService.setDeadline(projectList, subcommandRest1[0], formatter1.parse(subcommandRest1[1]));
                break;
            case "today":
                viewService.viewTasksDueToday(projectList, out);
                break;
            case "delete":
                deleteService.deleteTask(projectList, taskIds, commandRest[1], out);
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void help() {
        out.println("Commands:");
        out.println("  view_by_project");
        out.println("  view_by_date");
        out.println("  view_by_deadline");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task ID> <task description> (<task ID> should be alpha-numeric)");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  deadline <task ID> <date> (Use dd/MM/yyyy format)");
        out.println("  today");
        out.println("  delete <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
