package com.codurance.training.tasks;

import com.codurance.training.tasks.command.*;
import com.codurance.training.tasks.domain.ProjectsToTasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CommandLineParserShould {

    private static final ProjectsToTasks PROJECTS_TO_TASKS_NOT_USED = null;

    private final String commandName;
    private final Command expectedCommand;

    @Parameters(name="The parser returns a command of type {1} for the command-line command {0}.")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {"show", new TaskListShowCommand("show", PROJECTS_TO_TASKS_NOT_USED)},
                        {"add", new TaskListAddCommand("add", PROJECTS_TO_TASKS_NOT_USED)},
                        {"project xxx", new TaskListAddProjectCommand("project xxx", PROJECTS_TO_TASKS_NOT_USED)},
                        {"task xxx", new TaskListAddTaskCommand("task xxx", PROJECTS_TO_TASKS_NOT_USED)},
                        {"check", new TaskListCheckCommand("check", PROJECTS_TO_TASKS_NOT_USED)},
                        {"uncheck", new TaskListUnCheckCommand("uncheck", PROJECTS_TO_TASKS_NOT_USED)},
                        {"help", new HelpCommand("help")},
                }
        );
    }

    public CommandLineParserShould(String commandName, Command expectedCommand) {
        this.commandName = commandName;
        this.expectedCommand = expectedCommand;
    }

    @Test public void
    return_task_list_show_command_when_show_command_is_typed() {
        CommandLine commandLine = new CommandLine(commandName, PROJECTS_TO_TASKS_NOT_USED);
        Command command = commandLine.getCommand();

        assertThat(command, instanceOf(expectedCommand.getClass()));
    }
}