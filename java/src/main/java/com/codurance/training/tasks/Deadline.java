package com.codurance.training.tasks;

import java.util.Date;
import java.util.Map;

public class Deadline implements DeadlineService {

    @Override
    public void setDeadline(Map<Long, Project> projectList, String taskId, Date deadline) {
        for (Map.Entry<Long, Project> e : projectList.entrySet()) {
            for(Task task: e.getValue().getTasks()) {
                if(task.getUniqueId().equals(taskId)) {
                    task.setDeadline(deadline);
                    return;
                }
            }
        }
    }
}
