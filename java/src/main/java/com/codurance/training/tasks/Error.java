package com.codurance.training.tasks;

import java.io.PrintWriter;

public class Error {
    public void error(String command, PrintWriter out) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
