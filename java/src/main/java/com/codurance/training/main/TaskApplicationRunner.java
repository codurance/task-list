package com.codurance.training.main;

import com.codurance.training.tasks.TaskList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TaskApplicationRunner {
    public static void main(String[] args) {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(System.out);
            new TaskList(in,out).run();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}