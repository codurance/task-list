package com.codurance.training.tasks.command;

public class UnknownCommand implements Command {
    private String name;

    public UnknownCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(CommandLine commandLine) {}

    @Override
    public String toString() {
        return name;
    }
}
