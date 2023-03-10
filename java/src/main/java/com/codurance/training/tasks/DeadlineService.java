package com.codurance.training.tasks;

import java.util.Date;
import java.util.Map;

public interface DeadlineService {
    void setDeadline(Map<Long, Project> projectList, String taskId, Date deadline);
}
