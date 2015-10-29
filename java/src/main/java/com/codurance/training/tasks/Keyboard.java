package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Keyboard {
    private BufferedReader input;
    private InputStreamReader inputStreamReader = new InputStreamReader(System.in);

    public Keyboard() {
        input = new BufferedReader(inputStreamReader);
    }

    public Keyboard(InputStreamReader inputStreamReader) {
        this.inputStreamReader = inputStreamReader;
        input = new BufferedReader(inputStreamReader);
    }

    public String readLine() throws IOException {
        return input.readLine();
    }

    public void read(char[] buffer, int start, int length) throws IOException {
        input.read(buffer, start, length);
    }
}
