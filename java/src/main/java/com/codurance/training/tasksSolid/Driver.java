package com.codurance.training.tasksSolid;

import com.codurance.training.tasksSolid.manager.TaskRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Driver {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskRunner(in, out).run();
    }
}
