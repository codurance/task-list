package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.ArrayList;

public class addProject implements AddService{
    @Override
    public void CRUD(String commandLine, PrintWriter out) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String name = subcommandRest[1];
        TaskList.tasks.put(name, new ArrayList<>());
    }
}
