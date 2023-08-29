package com.codurance.training.tasks.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;

import com.codurance.training.tasks.entities.Task;
import com.codurance.training.tasks.services.*;



class TaskOperationImpl implements TaskOperationInterface {

    private static Map<String, List<Task>> tasks = new LinkedHashMap<>();

    private static PrintWriter out;

    private static int lastId;
    
    TaskOperationImpl(Map<String, List<Task>> tasks) {
        this.tasks = tasks;
        this.lastId = lastId;
        out = new PrintWriter(System.out);
    }
    
    public void show(PrintWriter out) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    public void add(Item item,int lastId) {
        PrintWriter out = new PrintWriter(System.out);
        item.add(tasks,"check","check",out,lastId);
    }

    public void check(Checker checker) {
        checker.check();
    }

    public void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    public static void setDone(String idString, boolean done) {
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

    public void error(String error) {
        out.printf("I don't know what the command \"%s\" is.",error);
        out.println();
    }
}    