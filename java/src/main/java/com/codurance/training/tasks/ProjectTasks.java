package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.codurance.training.tasks.Task.NOT_DONE;

public class ProjectTasks {

    private static final String COULD_NOT_FIND_A_PROJECT = "Could not find a project with the name \"%s\".";
    private static final String COULD_NOT_FIND_A_TASK = "Could not find a task with an ID of %d.";

    private static final String PRINT_LINE_FORMATTER = "    [%c] %d: %s%n";

    private static final char TASK_DONE_SYMBOL = 'x';
    private static final char TASK_NOT_DONE_SYMBOL = ' ';

    private final Map<String, List<Task>> projectsToTasks = new LinkedHashMap<>();

    private List<Task> getTasksFor(String projectName) {
        return projectsToTasks.get(projectName);
    }

    public void add(String projectName, List<Task> tasks) {
        projectsToTasks.put(projectName, tasks);
    }

    public void addTask(long nextTaskId, String project, String description, PrintWriter out) {
        List<Task> projectTasks = getTasksFor(project);
        if (projectTasks == null) {
            out.printf(COULD_NOT_FIND_A_PROJECT, project);
            out.println();
            return;
        }
        projectTasks.add(new Task(nextTaskId, description, NOT_DONE));
    }

    void setDone(String idString, boolean done, PrintWriter out) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : entries()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf(COULD_NOT_FIND_A_TASK, id);
        out.println();
    }

    public Iterable<? extends Map.Entry<String, List<Task>>> entries() {
        return projectsToTasks.entrySet();
    }

    void show(PrintWriter out) {
        for (Map.Entry<String, List<Task>> project : entries()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf(PRINT_LINE_FORMATTER, (
                        task.isDone()
                                ? TASK_DONE_SYMBOL
                                : TASK_NOT_DONE_SYMBOL),
                        task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}