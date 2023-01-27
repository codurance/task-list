package com.codurance.training.tasks.command;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskList;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ShowCommand implements Command {
    private final PrintWriter out;

    public ShowCommand(PrintWriter out) {
        this.out = out;
    }

    @Override
    public boolean appliesTo(String commandLine) {
        return commandLine.startsWith("show");
    }

    @Override
    public final void execute(String commandLine, TaskList taskList) {
        show(filterTasks(taskList));
        
    }

    protected Map<String, List<Task>> filterTasks(TaskList taskList) {
        return taskList.getTasks();
    }

    private void show(Map<String, List<Task>> tasks) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}
