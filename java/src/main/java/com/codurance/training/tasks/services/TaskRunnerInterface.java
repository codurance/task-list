package com.codurance.training.tasks.services;

import java.io.BufferedReader;
import java.io.PrintWriter;

interface TaskRunnerInterface {
    void run(PrintWriter out, BufferedReader in,int lastId);
}