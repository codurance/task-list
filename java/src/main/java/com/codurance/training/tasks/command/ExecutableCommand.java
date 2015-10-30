package com.codurance.training.tasks.command;

public interface ExecutableCommand extends NonExecutableCommand {

    String CMD_QUIT = "quit";
    String CMD_SHOW = "show";
    String CMD_ADD = "add";
    String CMD_CHECK = "check";
    String CMD_UNCHECK = "uncheck";
    String CMD_HELP = "help";

    String SUB_CMD_PROJECT = "project";
    String SUB_CMD_TASK = "task";

    void execute(CommandLine commandLine);
}