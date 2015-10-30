package com.codurance.training.tasks.command.nonexecutable;

public class UnknownCommand implements NonExecutableCommand {
    private String name;

    public UnknownCommand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}