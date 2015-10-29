package com.codurance.training.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ApplicationTest {
    private static final String PROMPT = "> ";
    private static final boolean AUTO_FLUSH = true;

    private final PipedOutputStream inStream = new PipedOutputStream();
    private final Screen inWriter = getOutputSource(inStream);

    private final PipedInputStream outStream = new PipedInputStream();
    private final Keyboard outReader = getInputSource(outStream);

    private Thread applicationThread;

    public ApplicationTest() throws IOException {
        Keyboard inputSource = getInputSource(new PipedInputStream(inStream));
        Screen outputTarget = getOutputSource(new PipedOutputStream(outStream));

        TaskList taskList = new TaskList(inputSource, outputTarget);
        applicationThread = new Thread(taskList);
    }

    private Screen getOutputSource(PipedOutputStream out) throws IOException {
        return new Screen(new PrintWriter(out, AUTO_FLUSH));
    }

    private Keyboard getInputSource(InputStream in) throws IOException {
        return new Keyboard(new InputStreamReader(in));
    }

    @Before public void
    start_the_application() {
        applicationThread.start();
    }

    @After public void
    kill_the_application() throws IOException, InterruptedException {
        if (applicationThread == null || !applicationThread.isAlive()) {
            return;
        }

        applicationThread.interrupt();
        throw new IllegalStateException("The application is still running.");
    }

    @Test(timeout = 1000) public void
    it_works() throws IOException {
        execute("help");

        readLines("Commands:",
                "  show",
                "  add project <project name>",
                "  add task <project name> <task description>",
                "  check <task ID>",
                "  uncheck <task ID>",
                ""
        );

        execute("show");

        execute("add project secrets");
        execute("add task secrets Eat more donuts.");
        execute("add task secrets Destroy all humans.");

        execute("show");
        readLines(
            "secrets",
            "    [ ] 1: Eat more donuts.",
            "    [ ] 2: Destroy all humans.",
            ""
        );

        execute("add project training");
        execute("add task training Four Elements of Simple Design");
        execute("add task training SOLID");
        execute("add task training Coupling and Cohesion");
        execute("add task training Primitive Obsession");
        execute("add task training Outside-In TDD");
        execute("add task training Interaction-Driven Design");

        execute("check 1");
        execute("check 3");
        execute("check 5");
        execute("check 6");

        execute("show");
        readLines(
                "secrets",
                "    [x] 1: Eat more donuts.",
                "    [ ] 2: Destroy all humans.",
                "",
                "training",
                "    [x] 3: Four Elements of Simple Design",
                "    [ ] 4: SOLID",
                "    [x] 5: Coupling and Cohesion",
                "    [x] 6: Primitive Obsession",
                "    [ ] 7: Outside-In TDD",
                "    [ ] 8: Interaction-Driven Design",
                ""
        );

        execute("check 9");
        readLines("Could not find a task with an ID of 9.");

        execute("uncheck 1");
        execute("uncheck 3");
        execute("show");
        readLines(
                "secrets",
                "    [ ] 1: Eat more donuts.",
                "    [ ] 2: Destroy all humans.",
                "",
                "training",
                "    [ ] 3: Four Elements of Simple Design",
                "    [ ] 4: SOLID",
                "    [x] 5: Coupling and Cohesion",
                "    [x] 6: Primitive Obsession",
                "    [ ] 7: Outside-In TDD",
                "    [ ] 8: Interaction-Driven Design",
                ""
        );

        execute("add task unknownProject SomeTask");
        readLines("Could not find a project with the name \"unknownProject\".");

        execute("someUnknownCommand");
        readLines("I don't know what the command \"someUnknownCommand\" is.");

        execute("quit");
    }

    private void execute(String command) throws IOException {
        read(PROMPT);
        write(command);
    }

    private void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        outReader.read(buffer, 0, length);
        assertThat(String.valueOf(buffer), is(expectedOutput));
    }

    private void readLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + lineSeparator());
        }
    }

    private void write(String input) {
        inWriter.println(input);
    }
}
