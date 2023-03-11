package com.codurance.training.error;

import java.io.PrintWriter;

public class Error implements ErrorInterface {
    public void showError(PrintWriter out,String commandLine) {
        out.printf("I don't know what the command \"%s\" is.", commandLine);
        out.println();
    }
}
