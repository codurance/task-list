package com.codurance.training.commandLine;

import java.io.PrintWriter;

public interface CommandExecuterInterface {
    void execute(PrintWriter out, String commandLine);
}
