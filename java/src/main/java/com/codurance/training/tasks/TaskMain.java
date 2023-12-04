package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TaskMain {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        IdGenerator idGenerator=new TaskIdGenerator();
        TaskManager taskManager = new TaskManagerImpl(idGenerator,out);
        TaskRunner taskRunner = new TaskRunnerImpl(in, out, taskManager);
        taskRunner.run();
    }
}
