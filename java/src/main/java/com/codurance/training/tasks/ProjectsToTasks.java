package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.codurance.training.tasks.Tasks.NO_TASKS;

public class ProjectsToTasks {

    private static final String COULD_NOT_FIND_A_TASK = "Could not find a task with an ID of %d.";
    private static final String COULD_NOT_FIND_A_PROJECT = "Could not find a project with the name \"%s\".";

    private final Map<Project, Tasks> projectsToTasksMapper = new LinkedHashMap<>();
    private final Screen out;

    public ProjectsToTasks(Screen out) {
        this.out = out;
    }

    private Tasks getTasksFor(Project project) {
        return projectsToTasksMapper.get(project);
    }

    public void add(Project project, Tasks tasks) {
        projectsToTasksMapper.put(project, tasks);
    }

    public void addTask(long nextTaskId, Project project, String taskDescription) {
        Tasks projectTasks = getTasksFor(project);
        if (projectTasks == NO_TASKS) {
            out.printf(COULD_NOT_FIND_A_PROJECT, project);
            out.println();
        } else {
            projectTasks.add(new Task(nextTaskId, taskDescription));
        }
    }

    public Iterable<? extends Map.Entry<Project, Tasks>> entries() {
        return projectsToTasksMapper.entrySet();
    }

    public void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<Project, Tasks> project : entries()) {
            Tasks tasks = project.getValue();
            if (tasks.setDone(id, done)) return;
        }
        out.printf(COULD_NOT_FIND_A_TASK, id);
        out.println();
    }

    public void show() {
        for (Map.Entry<Project, Tasks> project : entries()) {
            out.println(project.getKey());
            Tasks tasks = project.getValue();
            tasks.show(out);
            out.println();
        }
    }
}