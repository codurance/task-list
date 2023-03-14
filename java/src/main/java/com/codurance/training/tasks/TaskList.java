package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    public static Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    public static long lastId = 0;

    public AddService addTask = new AddTask();
    public AddService addProject = new addProject();
    public CommandService errorCommand = new CommandServiceImpl();
    public HelpService help = new HelpServiceImpl();
    public CheckService check = new CheckServiceImpl();
    public ShowService showSer = new ShowServiceImpl();
    public AddService addDeadline = new DeadLineImpl();
    public AddService customId = new CustomIdentifierImpl();
    public AddService deleteImpl = new DeleteImpl();
    public ShowByDateService showByDeadLine = new ShowByDeadLine();
    public ShowByDateService showByDateCreated = new ShowByCreatedDate();
    public ShowByProjectService showByProjectId = new ShowByProjectId();


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
                showSer.show(out);
                break;
            case "add":
                String[] subcommandRest = commandLine.split(" ", 2);
                String subcommand = subcommandRest[0];
                if (subcommand.equals("project")) {
                    addProject.CRUD(commandRest[1],out);
                } else if (subcommand.equals("task")) {
                    addTask.CRUD(commandRest[1],out);
                }
                break;
            case "check":
                check.setDone(commandRest[1],true,out);
                break;
            case "uncheck":
                check.setDone(commandRest[1],false,out);
                break;
            case "help":
                help.userHelp(out);
                break;
            case "deadline" :
                addDeadline.CRUD(commandRest[1],out);
                break;
            case "customid":
                customId.CRUD(commandRest[1],out);
                break;
            case "delete":
                deleteImpl.CRUD(commandRest[1],out);
                break;
            case "viewby" :
                subcommandRest = commandRest[1].split(" ", 2);
                subcommand = subcommandRest[0];
                switch (subcommand) {
                    case "date":
                        showByDateCreated.show(out, subcommandRest[1]);
                        break;
                    case "deadline":
                        showByDeadLine.show(out, subcommandRest[1]);
                        break;
                    case "project":
                        showByProjectId.show(out,subcommandRest[1]);
                        break;
                }
                break;
            default:
                errorCommand.error(command, out);
                break;
        }
    }
}
