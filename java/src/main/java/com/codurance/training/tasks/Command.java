package com.codurance.training.tasks;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Command {

    public static final Command CMD_QUIT = new Command("quit");
    public static final Command CMD_SHOW = new Command("show");
    public static final Command CMD_ADD = new Command("add");
    public static final Command CMD_CHECK = new Command("check");
    public static final Command CMD_UNCHECK = new Command("uncheck");
    public static final Command CMD_HELP = new Command("help");

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

    public boolean contains(String value) {
        return name.equals(value);
    }
}
