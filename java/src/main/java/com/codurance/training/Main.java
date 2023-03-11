package com.codurance.training;

import com.codurance.training.commandLine.CommandRunner;

public final class Main implements Runnable {

    public static void main(String[] args) {
       new Main().run();
    }
    public void run() {
        CommandRunner commandRunner = new CommandRunner();
        commandRunner.run();

    }

}
