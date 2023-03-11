package com.codurance.training.operations.showprojects;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ShowProjects implements ShowProjectsInterface {

    private final String pattern = "dd-MM-yyyy";
    private final SimpleDateFormat formatter=new SimpleDateFormat(pattern);
    @Override
    public void byProject(PrintWriter out, Map<String, List<Task>> tasks) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    @Override
    public void byDate(PrintWriter out,Map<String, List<Task>> tasks) {
        HashSet<String> dates=new HashSet();
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                dates.add(formatter.format(task.getCreatedDate()));
            }
        }

        printProjects(out,dates,tasks);
    }

    @Override
    public void byDeadline(PrintWriter out, Map<String, List<Task>> tasks) {
        HashSet<String> deadlines=new HashSet();
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if(task.getDeadline()!=null)
                    deadlines.add(formatter.format(task.getDeadline()));
            }
        }

        printProjects(out,deadlines,tasks);
    }

    void printProjects(PrintWriter out,HashSet<String> dates,Map<String, List<Task>> tasks){
        for(String dateString: dates){
            out.println(dateString);
            for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
                for (Task task : project.getValue()) {
                    if(dateString.equals(formatter.format(task.getDeadline())))
                        out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
                }
                out.println();
            }
        }
    }
}
