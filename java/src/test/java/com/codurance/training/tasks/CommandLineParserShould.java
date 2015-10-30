package com.codurance.training.tasks;

import com.codurance.training.tasks.command.*;
import com.codurance.training.tasks.domain.ProjectsToTasks;
import com.codurance.training.tasks.io.Screen;
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
    private static final Screen SCREEN_IS_NOT_AVAILABLE = null;

    private final String commandName;
    private final NonExecutableCommand expectedExecutableCommand;

    @Parameters(name="The parser returns a command of type {1} for the command-line command {0}.")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {"show", new TaskListShowExecutableCommand(PROJECTS_TO_TASKS_NOT_USED)},
                        {"add", new TaskListAddExecutableCommand(SCREEN_IS_NOT_AVAILABLE, PROJECTS_TO_TASKS_NOT_USED)},
                        {"project xxx", new TaskListAddProjectExecutableCommand(PROJECTS_TO_TASKS_NOT_USED)},
                        {"task xxx", new TaskListAddTaskExecutableCommand(SCREEN_IS_NOT_AVAILABLE, PROJECTS_TO_TASKS_NOT_USED)},
                        {"check", new TaskListCheckExecutableCommand(PROJECTS_TO_TASKS_NOT_USED)},
                        {"uncheck", new TaskListUnCheckExecutableCommand(PROJECTS_TO_TASKS_NOT_USED)},
                        {"help", new HelpExecutableCommand(SCREEN_IS_NOT_AVAILABLE)},
                        {"quit", new QuitCommand()},
                        {"unknowncommand", new UnknownCommand("unknowncommand")},

                }
        );
    }

    public CommandLineParserShould(String commandName, NonExecutableCommand expectedExecutableCommand) {
        this.commandName = commandName;
        this.expectedExecutableCommand = expectedExecutableCommand;
    }

    @Test public void
    return_task_list_show_command_when_show_command_is_typed() {
        CommandLine commandLine = new CommandLine(commandName, SCREEN_IS_NOT_AVAILABLE, PROJECTS_TO_TASKS_NOT_USED);
        NonExecutableCommand executableCommand = commandLine.getCommand();

        assertThat(executableCommand, instanceOf(expectedExecutableCommand.getClass()));
    }
}