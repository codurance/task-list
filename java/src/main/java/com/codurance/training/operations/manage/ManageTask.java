package com.codurance.training.operations.manage;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ManageTask implements ManageTaskInterface {
    public void manage(PrintWriter out,String idString, boolean done,Map<String, List<Task>> tasks) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId().equals(idString)) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.", idString);
        out.println();
    }
}
