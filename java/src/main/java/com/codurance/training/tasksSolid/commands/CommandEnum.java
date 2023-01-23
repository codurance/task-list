package com.codurance.training.tasksSolid.commands;

public enum CommandEnum {
    SHOW,
    ADD,
    CHECK,
    UNCHECK,
    HELP,
    INVALID;

    public static CommandEnum getCommandEnum(String command) {
        for (CommandEnum commandEnum : values()) {
            if (commandEnum.name().equalsIgnoreCase(command)) {
                return commandEnum;
            }
        }
        return INVALID;
    }
}
