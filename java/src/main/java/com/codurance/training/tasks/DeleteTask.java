package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class DeleteTask implements DeleteService {

    @Override
    public void deleteTask(Map<Long, Project> projectList, List<String> taskIds, String command, PrintWriter out) {
        for (Map.Entry<Long, Project> e : projectList.entrySet()) {
            Project project = e.getValue();
            for(Task task:project.getTasks()) {
                if(task.getUniqueId().equals(command)) {
                    project.getTasks().remove(task);
                    taskIds.remove(command);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %s.", command);
        out.println();
    }
}
