package com.codurance.training.tasks;

import com.codurance.training.tasks.io.Keyboard;
import com.codurance.training.tasks.io.Screen;
import org.hamcrest.Matchers;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class IOConsole {
    private static final String PROMPT = "> ";

    private final Keyboard keyboard;
    private final Screen screen;

    public IOConsole(Keyboard keyboard, Screen screen) {
        this.keyboard = keyboard;
        this.screen = screen;
    }

    void execute(String command) throws IOException {
        read(PROMPT);
        write(command);
    }

    void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        keyboard.read(buffer, 0, length);
        assertThat(String.valueOf(buffer), Matchers.is(expectedOutput));
    }

    void readLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + System.lineSeparator());
        }
    }

    void write(String input) {
        screen.println(input);
    }
}