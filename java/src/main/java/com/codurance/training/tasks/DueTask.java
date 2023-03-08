package com.codurance.training.tasks;

import java.util.*;

public class DueTask {
    Map<String, Project> addedProjects ;

    public DueTask(Map<String, Project> addedProjects) {
        this.addedProjects = addedProjects;
    }

    public List<Task> getTaskDueToday(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Date today = c.getTime();

        List<Task> tasks = new ArrayList<>();
        for (Map.Entry<String, Project> project : addedProjects.entrySet()) {
            for (Task task : project.getValue().getTasks()) {
              if(task.getDeadline() != null && task.getDeadline().compareTo(today) == 0){
                  tasks.add(task);
              }
            }
        }
        return tasks;
    }
}
