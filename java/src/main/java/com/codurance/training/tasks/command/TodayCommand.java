package com.codurance.training.tasks.command;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskList;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TodayCommand extends ShowCommand {


    public TodayCommand(PrintWriter out) {
        super(out);
    }

    @Override
    public boolean appliesTo(String commandLine) {
        return commandLine.startsWith("today");
    }

    @Override
    protected Map<String, List<Task>> filterTasks(TaskList taskList) {
        Map<String, List<Task>> result = new LinkedHashMap<>();
        LocalDate now = LocalDate.now();
        for (Map.Entry<String, List<Task>> entry : taskList.getTasks().entrySet()) {
            List<Task> todayTasks = entry.getValue().stream().filter(task -> isEquals(now, task)).toList();
            if (!todayTasks.isEmpty()) {
                result.put(entry.getKey(), todayTasks);
            }
        }
        return result;
    }

    private boolean isEquals(LocalDate now, Task task) {
        return task.getDeadline() != null && task.getDeadline().equals(now);
    }
}
