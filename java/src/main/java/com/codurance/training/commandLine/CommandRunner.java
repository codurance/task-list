package com.codurance.training.commandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CommandRunner implements CommandRunnerInterface {
    private final BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    private final PrintWriter out=new PrintWriter(System.out);
    private static final String QUIT = "quit";
    private final CommandExecuter commandExecuter=new CommandExecuter();
    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            commandExecuter.execute(out,command);
        }
    }
}
