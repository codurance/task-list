package com.codurance.training.tasks;

import java.io.PrintWriter;

public class CommandServiceImpl implements CommandService{
    @Override
    public void error(String command, PrintWriter out) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
