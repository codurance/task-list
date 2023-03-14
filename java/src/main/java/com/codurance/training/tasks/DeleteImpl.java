package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import static com.codurance.training.tasks.TaskList.tasks;

public class DeleteImpl implements AddService{
    @Override
    public void CRUD(String id, PrintWriter out) {
            for (List<Task> taskList : tasks.values()) {
                Iterator<Task> iterator = taskList.iterator();
                while (iterator.hasNext()) {
                    Task task = iterator.next();
                    if (task.getId() == Integer.parseInt(id)) {
                        iterator.remove();
                        return;
                    }
                }
            }
            out.printf("Could not find a task with an ID of %s.", id);
    }
}
