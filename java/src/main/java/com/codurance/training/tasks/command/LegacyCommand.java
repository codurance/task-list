package com.codurance.training.tasks.command;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskList;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LegacyCommand implements Command {
    private final PrintWriter out;

    public LegacyCommand(PrintWriter out) {
        this.out = out;
    }

    @Override
    public boolean appliesTo(String commandLine) {
        return true;
    }

    @Override
    public void execute(String commandLine, TaskList taskList) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show(commandRest, taskList.getTasks());
                break;
            case "add":
                add(commandRest, taskList);
                break;
            case "check":
                check(commandRest[1], taskList.getTasks());
                break;
            case "uncheck":
                uncheck(commandRest[1], taskList.getTasks());
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void show(String[] commandRest, Map<String, List<Task>> tasks) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    private void add(String[] commandLine, TaskList taskList) {
        String[] subcommandRest = commandLine[1].split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1], taskList.getTasks());
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1], taskList);
        }
    }


    private void check(String idString, Map<String, List<Task>> tasks) {
        setDone(idString, true, tasks);
    }

    private void uncheck(String idString, Map<String, List<Task>> tasks) {
        setDone(idString, false, tasks);
    }


    private void addProject(String name, Map<String, List<Task>> tasks) {
        tasks.put(name, new ArrayList<Task>());
    }

    private void addTask(String project, String description, TaskList tasks) {
        List<Task> projectTasks = tasks.getTasks().get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(tasks.nextId(), description, false));
    }

    private void setDone(String idString, boolean done, Map<String, List<Task>> tasks) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
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
