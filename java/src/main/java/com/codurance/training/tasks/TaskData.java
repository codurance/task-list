package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskData {
    public final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    public long lastId = 0;

}
