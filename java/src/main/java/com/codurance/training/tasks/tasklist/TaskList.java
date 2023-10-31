package com.codurance.training.tasks.tasklist;

import com.codurance.training.tasks.task.ITask;
import com.codurance.training.tasks.task.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskList implements ITaskList {
    private final Map<String, List<ITask>> tasks = new LinkedHashMap<>();
    private long lastId = 0;

    public void addProject(String name) {
        tasks.put(name, new ArrayList<ITask>());
    }

    public void addTask(String project, String description) {
        List<ITask> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            System.out.printf("Could not find a project with the name \"%s\".%n", project);
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    public void check(String idString) {
        setDone(idString, true);
    }

    public void uncheck(String idString) {
        setDone(idString, false);
    }

    @Override
    public Map<String, List<ITask>> getTasks() {
        return tasks;
    }

    private long nextId() {
        return ++lastId;
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<ITask>> project : tasks.entrySet()) {
            for (ITask task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        System.out.printf("Could not find a task with an ID of %d.%n", id);
    }
}
