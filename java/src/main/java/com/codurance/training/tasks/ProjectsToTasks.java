package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.codurance.training.tasks.Task.NOT_DONE;

public class ProjectsToTasks {

    private static final String COULD_NOT_FIND_A_TASK = "Could not find a task with an ID of %d.";
    private static final String COULD_NOT_FIND_A_PROJECT = "Could not find a project with the name \"%s\".";

    private final Map<String, Tasks> projectsToTasksMapper = new LinkedHashMap<>();

    private Tasks getTasksFor(String projectName) {
        return projectsToTasksMapper.get(projectName);
    }

    public void add(String projectName, Tasks tasks) {
        projectsToTasksMapper.put(projectName, tasks);
    }

    public void addTask(long nextTaskId, String project, String description, PrintWriter out) {
        Tasks projectTasks = getTasksFor(project);
        if (projectTasks == null) {
            out.printf(COULD_NOT_FIND_A_PROJECT, project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextTaskId, description, NOT_DONE));
    }

    void setDone(String idString, boolean done, PrintWriter out) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, Tasks> project : entries()) {
            Tasks tasks = project.getValue();
            if (tasks.setDone(id, done, out)) return;
        }
        out.printf(COULD_NOT_FIND_A_TASK, id);
        out.println();
    }

    public Iterable<? extends Map.Entry<String, Tasks>> entries() {
        return projectsToTasksMapper.entrySet();
    }

    void show(PrintWriter out) {
        for (Map.Entry<String, Tasks> project : entries()) {
            out.println(project.getKey());
            Tasks tasks = project.getValue();
            tasks.show(out);
            out.println();
        }

    }

}