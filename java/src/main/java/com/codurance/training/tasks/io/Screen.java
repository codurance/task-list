package com.codurance.training.tasks.io;

import java.io.PrintWriter;

public class Screen {
    private PrintWriter printWriter;

    public Screen() {
        printWriter = new PrintWriter(System.out);
    }

    public Screen(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void print(String value) {
        printWriter.print(value);
        printWriter.flush();
    }

    public void printf(String formattedString, Object... values) {
        printWriter.printf(formattedString, values);
    }

    public void println() {
        printWriter.println();
    }

    public void println(Object value) {
        printWriter.println(value);
    }
}