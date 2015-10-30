package com.codurance.training.tasks.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Keyboard {
    private BufferedReader input;

    public Keyboard() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    public Keyboard(InputStreamReader inputStreamReader) {
        input = new BufferedReader(inputStreamReader);
    }

    public String readLine() throws IOException {
        return input.readLine();
    }

    public void read(char[] buffer, int offset, int length) throws IOException {
        input.read(buffer, offset, length);
    }
}
