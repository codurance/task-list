package com.codurance.training.operations.showprojects;

import com.codurance.training.task.Task;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TodayProjects implements TodayProjectsInterface{
    @Override
    public void show(PrintWriter out, Map<String, List<Task>> tasks) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat formatter=new SimpleDateFormat(pattern);

        String today =formatter.format(new Date());

        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if(task.getDeadline()!=null){
                    String deadlineString=formatter.format(task.getDeadline());
                    if(today.equals(deadlineString))
                        out.printf("    [%c] %s: %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), deadlineString);
                }

            }
            out.println();
        }

    }
}
