package com.codurance.training.tasks;

import java.util.List;
import java.util.Map;

public class TaskUtil extends TaskData{
    public void viewByDate() {
        //need info about the date factor as no date fields are present
    }

    public void viewByDeadline() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                if(task.getDeadline() != null)
                    this.printTask(task);
            }
            out.println();
        }
    }

    public void viewByProject() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                this.printTask(task);
            }
            out.println();
        }
    }

    public long nextId() {
        return ++lastId;
    }

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

    public void printTask(Task task){
        out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
    }
}
