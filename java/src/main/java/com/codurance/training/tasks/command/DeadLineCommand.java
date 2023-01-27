package com.codurance.training.tasks.command;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskList;
import java.io.PrintWriter;
import java.time.LocalDate;

public class DeadLineCommand implements Command {
    private final PrintWriter out;

    public DeadLineCommand(PrintWriter out) {
        this.out = out;
    }

    @Override
    public boolean appliesTo(String commandLine) {
        return commandLine.startsWith("deadline");
    }

    @Override
    public void execute(String commandLine, TaskList taskList) {
        CommandData data = parse(commandLine);
        Task task = taskList.getTaskById(data.id);
        task.setDeadline(data.deadline);
    }

    private CommandData parse(String commandLine) {
        String[] split = commandLine.split(" ");
        return new CommandData(Long.parseLong(split[1]), LocalDate.parse(split[2]));
    }

    private static class CommandData {
        private final Long id;
        private final LocalDate deadline;

        private CommandData(Long id, LocalDate date) {
            this.id = id;
            this.deadline = date;
        }
    }
}
