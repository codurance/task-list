package com.codurance.training.operations.manage;

import com.codurance.training.task.Task;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ManageDeadlineInterface {
    void manage(String idString, String deadlineString, Map<String, List<Task>> tasks) throws ParseException;
}
