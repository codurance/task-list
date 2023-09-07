package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskData {
    public Map<String, List<Task>> tasks = new LinkedHashMap<>();
    public long lastId = 0;

    public PrintWriter out = new PrintWriter(System.out);

}
