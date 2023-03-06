package com.codurance.training.tasks;


import java.io.PrintWriter;
        import java.util.List;
        import java.util.Map;

public class ShowTaskWithDeadlines implements Show{
    @Override
    public void show(PrintWriter out, Map<String, List<Task>> tasks) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                String deadline = task.getDeadLine();
                out.printf("    [%c] %d: %s - { deadline : %s }%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(),deadline);
            }
            out.println();
        }
    }
}
