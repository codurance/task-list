package com.codurance.training.tasks.command;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Command {

    public static final String CMD_QUIT = "quit";
    public static final String CMD_SHOW = "show";
    public static final String CMD_ADD = "add";
    public static final String CMD_CHECK = "check";
    public static final String CMD_UNCHECK = "uncheck";
    public static final String CMD_HELP = "help";

    public static final String SUB_CMD_PROJECT = "project";
    public static final String SUB_CMD_TASK = "task";

    private String name;

    public Command(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        return new EqualsBuilder()
                .append(name, command.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    public void execute(CommandLine... subCommandLine) {}
}
