package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TaskListInfoServiceImpl implements TaskListInfoService{

    private final PrintWriter out;
    private final Map<String, List<Task>> tasks;

    public TaskListInfoServiceImpl(PrintWriter out,  Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }

    public void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
           out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    @Override
    public void showDueTasksForToday(String commandLine) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String todayDate = dateFormatter.format(date);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                String deadline = task.getDeadline();

                if(deadline.equals(todayDate)) {
                    out.printf("    [%c] %d: %s%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), "{" + deadline + "}");
                }
            }
            out.println();
        }
    }

    @Override
    public void viewTasksByDeadline() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                String deadline = task.getDeadline();
                out.printf("    [%c] %d: %s%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), "{" + deadline + "}");
            }
            out.println();
        }
    }
}
