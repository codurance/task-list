package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static com.codurance.training.tasks.TaskList.tasks;

public class ShowByProjectId implements ShowByProjectService{
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public void show(PrintWriter out, String projectId) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            if(project.getKey().equals(projectId)) {
                out.println(project.getKey());
                for (Task task : project.getValue()) {
                    out.printf("    [%c] %d( %s ): %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(), (task.getCustomId() != null ? task.getCustomId() : ""), task.getDescription(), (task.getDeadline() != null ? dateFormatter.format(task.getDeadline()) : ""));
                }
                out.println();
                return;
            }
        }
        out.println("cannot find project with projectId "+projectId);
    }
}
