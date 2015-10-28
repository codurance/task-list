package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.codurance.training.tasks.Task.NOT_DONE;

public class ProjectsToTasks {

    private static final String COULD_NOT_FIND_A_TASK = "Could not find a task with an ID of %d.";
    private static final String COULD_NOT_FIND_A_PROJECT = "Could not find a project with the name \"%s\".";

    private final Map<Project, Tasks> projectsToTasksMapper = new LinkedHashMap<>();
    private final PrintWriter out;

    public ProjectsToTasks(PrintWriter out) {
        this.out = out;
    }

    private Tasks getTasksFor(Project project) {
        return projectsToTasksMapper.get(project);
    }

    public void add(Project project, Tasks tasks) {
        projectsToTasksMapper.put(project, tasks);
    }

    public void addTask(long nextTaskId, Project project, String description) {
        Tasks projectTasks = getTasksFor(project);
        if (projectTasks == null) {
            out.printf(COULD_NOT_FIND_A_PROJECT, project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextTaskId, description, NOT_DONE));
    }

    void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<Project, Tasks> project : entries()) {
            Tasks tasks = project.getValue();
            if (tasks.setDone(id, done, out)) return;
        }
        out.printf(COULD_NOT_FIND_A_TASK, id);
        out.println();
    }

    public Iterable<? extends Map.Entry<Project, Tasks>> entries() {
        return projectsToTasksMapper.entrySet();
    }

    void show() {
        for (Map.Entry<Project, Tasks> project : entries()) {
            out.println(project.getKey());
            Tasks tasks = project.getValue();
            tasks.show(out);
            out.println();
        }
    }
}