package com.codurance.training.tasks;

public class CommandLine {

    private static final String COMMAND_SEPARATOR = " ";
    private static final int TIMES_TO_APPLY_SEPARATOR = 2;

    private static final int COMMAND = 0;
    private static final int FIRST_PARAMETER = 1;

    private final String value;

    public CommandLine(String value) {
        this.value = value;
    }

    public boolean contains(Command command) {
        return command.contains(value);
    }

    public Command getCommand() {
        return parseCommandLineForCommand();
    }

    public String getFirstParameter() {
        return parseCommandLineFor((FIRST_PARAMETER));
    }

    private String parseCommandLineFor(int index) {
        String[] commandLineSplit = parseCommandLine();
        return commandLineSplit[index];
    }

    private Command parseCommandLineForCommand() {
        String[] commandLineSplit = parseCommandLine();
        return new Command(commandLineSplit[COMMAND]);
    }

    private String[] parseCommandLine() {
        return value.split(COMMAND_SEPARATOR, TIMES_TO_APPLY_SEPARATOR);
    }

    @Override
    public String toString() {
        return value;
    }
}
