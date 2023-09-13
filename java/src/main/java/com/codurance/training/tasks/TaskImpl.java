package com.codurance.training.tasks;

import java.util.*;

import static java.lang.System.out;

public class TaskImpl implements AddTask, CheckTask, UncheckTask, ShowTask , TaskSetDone, DeleteTask{

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private long lastId = 0;
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

    private void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    private long nextId() {
        return ++lastId;
    }

    private void addTask(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
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
    public void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    @Override
    public void uncheck(String idString) {
       setDone(idString, false);
    }

    @Override
    public void setDone(String idString, boolean done) {
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

    @Override
    public void delete(String stringId) {
        int id = Integer.parseInt(stringId);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            List<Task> projectTasks = project.getValue();
            Iterator<Task> iterator = projectTasks.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task.getId() == id) {
                    iterator.remove(); // Remove the task from the list
                    return;
                }
            }
        }
    }
}
