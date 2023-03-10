package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class View implements ViewService {

    @Override
    public void viewTasksByProject(Map<Long, Project> projectList, PrintWriter out) {
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");

        for (Map.Entry<Long, Project> e : projectList.entrySet()) {
            Project project = e.getValue();
            out.printf("%d: %s%n", project.getId(), project.getName());
            for (Task task : project.getTasks()) {
                String deadlineDateString = "";
                if(task.getDeadline()!=null) {
                    deadlineDateString = formatter1.format(task.getDeadline());
                }
                out.printf("    [%c] %s: %s, date: %s, deadline: %s%n", (task.isDone() ? 'x':' '), task.getUniqueId(), task.getDescription(), formatter1.format(task.getDate()), deadlineDateString);
            }
            out.println();
        }
    }

    @Override
    public void viewTasksDueToday(Map<Long, Project> projectList, PrintWriter out) {
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        for (Map.Entry<Long, Project> e : projectList.entrySet()) {
            Project project = e.getValue();
            out.printf("%d: %s%n", project.getId(), project.getName());
            int count = 0;
            for (Task task : project.getTasks()) {
                if(task.getDeadline()!=null && formatter1.format(task.getDeadline()).equals(formatter1.format(new Date()))) {
                    count++;
                    out.printf("    [%c] %s: %s: date: %s%n", (task.isDone() ? 'x':' '), task.getUniqueId(), task.getDescription(), formatter1.format(task.getDate()));
                }
            }
            if(count == 0) {
                out.printf("    No tasks in this project due today");
            }
            out.println();
        }
    }

    @Override
    public void viewTasksByDate(Map<Long, Project> projectList, PrintWriter out) {
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Map<Date, List<Task>> hashMap = new TreeMap<>();

        for (Map.Entry<Long, Project> e : projectList.entrySet()) {
            Project project = e.getValue();
            for (Task task : project.getTasks()) {
                List<Task> tasks = hashMap.getOrDefault(task.getDate(), new ArrayList<>());
                tasks.add(task);
                hashMap.put(task.getDate(), tasks);
            }
        }

        for (Map.Entry<Date, List<Task>> e : hashMap.entrySet()) {
            out.printf("%s:%n", formatter1.format(e.getKey()));
            for(Task task: e.getValue()) {
                String deadlineDateString = "";
                if(task.getDeadline()!=null) {
                    deadlineDateString = formatter1.format(task.getDeadline());
                }
                out.printf("           [%c] %s, project: %s, deadline: %s%n", (task.isDone() ? 'x':' '), task.getUniqueId(), projectList.get(task.getProjectId()).getName(), deadlineDateString);
            }
        }
    }

    @Override
    public void viewTasksByDeadline(Map<Long, Project> projectList, PrintWriter out) {
        SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
        Map<String, List<Task>> hashMap = new TreeMap<>();

        for (Map.Entry<Long, Project> e : projectList.entrySet()) {
            Project project = e.getValue();
            for (Task task : project.getTasks()) {
                String deadlineString = "null";
                if(task.getDeadline()!=null) {
                    deadlineString = formatter1.format(task.getDeadline());
                }
                List<Task> tasks = hashMap.getOrDefault(deadlineString, new ArrayList<>());
                tasks.add(task);
                hashMap.put(deadlineString, tasks);
            }
        }

        for (Map.Entry<String, List<Task>> e : hashMap.entrySet()) {
            out.printf("%s:%n", e.getKey());
            for(Task task: e.getValue()) {
                out.printf("           [%c] %s, project: %s, date: %s%n", (task.isDone() ? 'x':' '), task.getUniqueId(), projectList.get(task.getProjectId()).getName(), formatter1.format(task.getDate()));
            }
        }
    }
}
