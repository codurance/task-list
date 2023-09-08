package com.codurance.training.actions.impl;

import com.codurance.training.actions.TaskActions;
import com.codurance.training.tasks.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class TaskActionsImpl implements TaskActions {
    private final PrintWriter out = new PrintWriter(System.out);

    private static long lastId = 0;

    private static final Map<String, List<Task>> tasksMap= new LinkedHashMap<>();
    @Override
    public void show(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 3);
        if(subcommandRest[1].equals("project")) {
            for (Map.Entry<String, List<Task>> project : tasksMap.entrySet()) {
                out.println(project.getKey());
                for (Task task : project.getValue()) {
                    // out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                    System.out.println(task.isDone() ? 'x' : " " + task.getId() + " " + task.getDescription() + " " + task.getDeadline() + " " + task.getCustomizableId());

                }
                out.println();
            }
        }else if(subcommandRest[1].equals("deadline")){
            showByDeadline();
        } else if (subcommandRest[1].equals("date")) {
            showByDate(subcommandRest[2]);
        }
    }
    @Override
    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }
    @Override
    public void addProject(String name) {
        tasksMap.put(name, new ArrayList<Task>());
    }

    private void addTask(String project, String description) {
        List<Task> projectTasks = tasksMap.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }
    @Override
    public void check(String idString) {
        setDone(idString, true);
    }
    @Override
    public void uncheck(String idString) {
        setDone(idString, false);
    }
    @Override
    public void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasksMap.entrySet()) {
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
    @Override
    public void help() {
        out.println("Commands:");
        out.println("  view by project");
        out.println("  view by date");
        out.println("  view by deadline");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println("  deadline <task ID>");
        out.println("  delete <task ID>");
        out.println("  customize <task ID> <customize ID>");
        out.println("  today");
        out.println();
    }
    @Override
    public void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    @Override
    public void deadline(String command) {
        try {
            String[] subCommand = command.split(" ", 2);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date deadlineDate = sdf.parse(subCommand[1]);
            int id = Integer.parseInt(subCommand[0]);
            for (Map.Entry<String, List<Task>> project : tasksMap.entrySet()) {
                for (Task task : project.getValue()) {
                    if (task.getId() == id) {
                        task.setDeadline(deadlineDate);
                        return;
                    }
                }
            }
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void today() {
        for (Map.Entry<String, List<Task>> project : tasksMap.entrySet()) {
            for (Task task : project.getValue()) {
                if (DateUtils.isSameDay(new Date(),task.getDeadline())) {
//                    out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                    System.out.println(task.isDone()?'x':" "+task.getId()+" "+task.getDescription()+" "+task.getDeadline()+" "+task.getCustomizableId());
                }
            }
            out.println();
        }
    }

    @Override
    public void delete(String command) {
        int id = Integer.parseInt(command);
        for (Map.Entry<String, List<Task>> project : tasksMap.entrySet()) {
            List<Task> taskList = project.getValue();
            taskList.removeIf(task -> task.getId()==id);
            }

    }

    @Override
    public void showByDeadline() {
        for (Map.Entry<String, List<Task>> project : tasksMap.entrySet()) {
            for (Task task : project.getValue()) {
                if(task.getDeadline()!=null) {
                    if (DateUtils.isSameDay(new Date(), task.getDeadline())) {
//                    out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                        System.out.println(task.isDone() ? 'x' : " " + task.getId() + " " + task.getDescription() + " " + task.getDeadline() + " " + task.getCustomizableId());
                    }
                }
            }
            out.println();
        }
    }

    @Override
    public void showByDate(String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(s);
            for (Map.Entry<String, List<Task>> project : tasksMap.entrySet()) {
                for (Task task : project.getValue()) {
                    if (DateUtils.isSameDay(date, task.getDeadline())) {
//                    out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                        System.out.println(task.isDone() ? 'x' : " " + task.getId() + " " + task.getDescription() + " " + task.getDeadline()+" "+task.getCustomizableId());
                    }
                }
                out.println();
            }
        }catch (ParseException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void customize(String command) {
        String[] subCommand = command.split(" ", 2);
        Pattern p = Pattern.compile(
                "[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        int id = Integer.parseInt(subCommand[0]);
        if((!p.matcher(subCommand[1]).find())&& !StringUtils.containsWhitespace(subCommand[1])) {
            for (Map.Entry<String, List<Task>> project : tasksMap.entrySet()) {
                for (Task task : project.getValue()) {
                    if (task.getId() == id) {
                        task.setCustomizableId(subCommand[1]);
                        return;
                    }
                }
            }
        }
    }

    public long nextId() {
        return ++lastId;
    }
}
