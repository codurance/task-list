package com.codurance.training.tasks;

import com.codurance.training.tasks.domain.TaskList;
import com.codurance.training.tasks.io.Keyboard;
import com.codurance.training.tasks.io.Screen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public final class ApplicationTest {

    private static final boolean AUTO_FLUSH = true;

    private final PipedOutputStream inStream = new PipedOutputStream();
    private final Screen inWriter = getOutputTarget(inStream);

    private final PipedInputStream outStream = new PipedInputStream();
    private final Keyboard outReader = getInputSource(outStream);

    private IOConsole ioConsole;

    private Thread applicationThread;

    public ApplicationTest() throws IOException {
        ioConsole = new IOConsole(outReader, inWriter);

        Keyboard keyboard = getInputSource(new PipedInputStream(inStream));
        Screen screen = getOutputTarget(new PipedOutputStream(outStream));
        TaskList taskList = new TaskList(keyboard, screen);

        applicationThread = new Thread(taskList);
    }

    private Screen getOutputTarget(PipedOutputStream out) throws IOException {
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
        ioConsole.execute("help");

        ioConsole.readLines("Commands:",
                "  show",
                "  add project <project name>",
                "  add task <project name> <task description>",
                "  check <task ID>",
                "  uncheck <task ID>",
                ""
        );

        ioConsole.execute("show");

        ioConsole.execute("add project secrets");
        ioConsole.execute("add task secrets Eat more donuts.");
        ioConsole.execute("add task secrets Destroy all humans.");

        ioConsole.execute("show");
        ioConsole.readLines(
                "secrets",
                "    [ ] 1: Eat more donuts.",
                "    [ ] 2: Destroy all humans.",
                ""
        );

        ioConsole.execute("add project training");
        ioConsole.execute("add task training Four Elements of Simple Design");
        ioConsole.execute("add task training SOLID");
        ioConsole.execute("add task training Coupling and Cohesion");
        ioConsole.execute("add task training Primitive Obsession");
        ioConsole.execute("add task training Outside-In TDD");
        ioConsole.execute("add task training Interaction-Driven Design");

        ioConsole.execute("check 1");
        ioConsole.execute("check 3");
        ioConsole.execute("check 5");
        ioConsole.execute("check 6");

        ioConsole.execute("show");
        ioConsole.readLines(
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

        ioConsole.execute("check 9");
        ioConsole.readLines("Could not find a task with an ID of 9.");

        ioConsole.execute("uncheck 1");
        ioConsole.execute("uncheck 3");
        ioConsole.execute("show");
        ioConsole.readLines(
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

        ioConsole.execute("add task unknownProject SomeTask");
        ioConsole.readLines("Could not find a project with the name \"unknownProject\".");

        ioConsole.execute("someUnknownCommand");
        ioConsole.readLines("I don't know what the command \"someUnknownCommand\" is.");

        ioConsole.execute("quit");
    }
}
