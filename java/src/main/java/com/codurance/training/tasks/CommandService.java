package com.codurance.training.tasks;

import java.io.PrintWriter;

public interface CommandService {
    public void error(String command, PrintWriter out);
}
