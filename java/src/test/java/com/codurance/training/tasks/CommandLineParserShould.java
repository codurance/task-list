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
    private final Command expectedCommand;

    @Parameters(name="The parser returns a command of type {1} for the command-line command {0}.")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {"show", new TaskListShowCommand(PROJECTS_TO_TASKS_NOT_USED)},
                        {"add", new TaskListAddCommand(SCREEN_IS_NOT_AVAILABLE, PROJECTS_TO_TASKS_NOT_USED)},
                        {"project xxx", new TaskListAddProjectCommand(PROJECTS_TO_TASKS_NOT_USED)},
                        {"task xxx", new TaskListAddTaskCommand(SCREEN_IS_NOT_AVAILABLE, PROJECTS_TO_TASKS_NOT_USED)},
                        {"check", new TaskListCheckCommand(PROJECTS_TO_TASKS_NOT_USED)},
                        {"uncheck", new TaskListUnCheckCommand(PROJECTS_TO_TASKS_NOT_USED)},
                        {"help", new HelpCommand(SCREEN_IS_NOT_AVAILABLE)},
                        {"quit", new QuitCommand()},
                        {"unknowncommand", new UnknownCommand("unknowncommand")},

                }
        );
    }

    public CommandLineParserShould(String commandName, Command expectedCommand) {
        this.commandName = commandName;
        this.expectedCommand = expectedCommand;
    }

    @Test public void
    return_task_list_show_command_when_show_command_is_typed() {
        CommandLine commandLine = new CommandLine(commandName, SCREEN_IS_NOT_AVAILABLE, PROJECTS_TO_TASKS_NOT_USED);
        Command command = commandLine.getCommand();

        assertThat(command, instanceOf(expectedCommand.getClass()));
    }
}