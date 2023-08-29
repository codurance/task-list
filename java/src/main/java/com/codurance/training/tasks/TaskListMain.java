package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.codurance.training.tasks.services.*;

import com.codurance.training.tasks.entities.Task;

public final class TaskListMain {

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();

    private static int lastId = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        TaskRunnerImpl taskRunner = new TaskRunnerImpl();
        taskRunner.run(out,in,lastId);
    }
}

    
    
