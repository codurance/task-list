package com.codurance.training.tasks;

import com.codurance.training.tasks.services.RunService;

import java.io.*;
import java.util.*;

public class MainApp {
    private final Map<String, List<Task>> tasks;
    private final BufferedReader in;
    private final PrintWriter out;

    public MainApp(Map<String, List<Task>> tasks, BufferedReader reader, PrintWriter writer) {
        this.tasks = tasks;
        this.in = reader;
        this.out = writer;
        new RunService(this.tasks, this.in, this.out).run();
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        Map<String, List<Task>> tasks = new HashMap<>();
        new MainApp(tasks, in, out);
    }
}
