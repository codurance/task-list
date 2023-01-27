package com.codurance.training.tasks.command;

import com.codurance.training.tasks.TaskList;

public interface Command {
    boolean appliesTo(String commandLine);

    void execute(String commandLine, TaskList taskList);
}
