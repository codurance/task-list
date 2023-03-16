package com.codurance.training.services.ServicesImpl;

import com.codurance.training.services.DueTasks;
import com.codurance.training.tasks.Task;
import com.codurance.training.util.DateFormatter;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DueTasksImpl implements DueTasks {
        private Map<String, List<Task>> tasks;
        private PrintWriter out;

        public DueTasksImpl(Map<String, List<Task>> tasks, PrintWriter out) {
            this.tasks = tasks;
            this.out = out;
        }

        @Override
        public void showTasksDueToday() {
            Date today = new Date();
            for (Map.Entry<String, List<Task>> project: tasks.entrySet()){
                out.println(project.getKey());
                for (Task task: project.getValue()){
                    if (task.getDeadLine() != null && DateFormatter.parseDate(task.getDeadLine()).equals(DateFormatter.parseDate(today))) {
                        out.printf("    [%c] %s: %s, createdDate: %s, deadline:%s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), task.getCreatedDate(), task.getDeadLine());
                    }
                }
                out.println();
            }
        }
}

