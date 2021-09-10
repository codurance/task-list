package com.codurance.training.tasks;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskListTest {
    @Mock
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    @Mock
    private PrintWriter out;

    @InjectMocks
    private TaskList taskList;

    @DisplayName("Add command")
    @Nested
    class addCommand {
        @Test
        public void createProjectAssignments() {
            //Given
            String projectCreationCommand = "add project assignments";

            //When
            taskList.execute(projectCreationCommand);

            //Then
            assertThat(taskList.tasks).containsKey("assignments");
        }

        @Test
        public void createTaskInAssignmentsProject() {
            //Given
            taskList.tasks.put("assignments", new ArrayList<>());
            String projectCreationCommand = "add task assignments english class";

            //When
            taskList.execute(projectCreationCommand);

            //Then
            List<Task> assignments = taskList.tasks.get("assignments");
            assertAll(
                    () -> assertThat(assignments).hasSize(1),
                    () -> assertThat(assignments.get(0).getDescription()).isEqualTo("english class"));
        }

        @Test
        public void showError_whenProjectDoesNotExists() {
            //Given
            taskList.tasks.put("assignments", new ArrayList<>());
            String projectCreationCommand = "add task wrongProject english class";

            //When
            taskList.execute(projectCreationCommand);

            //Then
            List<Task> assignments = taskList.tasks.get("assignments");
            verify(out).printf("Could not find a project with the name \"%s\".",
                    "wrongProject");
        }
    }

    @DisplayName("Show command")
    @Nested
    class showCommand {
        @Test
        public void showProjectsAndAssignments() {
            //Given
            taskList.tasks.put("assignments", List.of(new Task(1L, "description", false)));
            String projectCreationCommand = "show";

            //When
            taskList.execute(projectCreationCommand);

            //Then
            verify(out).println("assignments");
            verify(out).printf(
                    "    [%c] %d: %s%n",
                    ' ',
                    1L,
                    "description"
            );;
        }
    }

    @DisplayName("Check and uncheck tasks")
    @Nested
    class checkCommand {

        @Test
        public void checkTaskAsDone() {
            //Given
            taskList.tasks.put("test", List.of(new Task(1L, "description", false)));

            String projectCreationCommand = "check 1";

            //When
            taskList.execute(projectCreationCommand);

            //Then
            assertThat(taskList.tasks.get("test").get(0).isDone()).isTrue();
        }

        @Test
        public void unCheckTask() {
            //Given
            taskList.tasks.put("test", List.of(new Task(1L, "description", true)));

            String projectCreationCommand = "uncheck 1";

            //When
            taskList.execute(projectCreationCommand);

            //Then
            assertThat(taskList.tasks.get("test").get(0).isDone()).isFalse();
        }

        @Test
        public void displayErrorMessage_whenTheTaskIdIsNotFound() {
            //Given
            taskList.tasks.put("test", List.of(new Task(1L, "description", true)));

            String projectCreationCommand = "uncheck 99";

            //When
            taskList.execute(projectCreationCommand);

            //Then
            assertThat(taskList.tasks.get("test").get(0).isDone()).isTrue();
            verify(out).printf(
                    "Could not find a task with an ID of %d.",
                    99
            );
        }
    }

    @DisplayName("Help command")
    @Nested
    class helpCommand {
        @Test
        public void showAllCommandsAvailable_whenHelpCommandIsTyped() {
            //Given
            taskList.tasks.put("test", List.of(new Task(1L, "description", true)));

            String projectCreationCommand = "help";

            //When
            taskList.execute(projectCreationCommand);

            //Then
            verify(out).println("Commands:");
            verify(out).println("  show");
            verify(out).println("  add project <project name>");
            verify(out).println("  add task <project name> <task description>");
            verify(out).println("  uncheck <task ID>");
            verify(out).println("  check <task ID>");
        }
    }

    @DisplayName("Error message when command is unknown")
    @Nested
    class errorMessage {
        @Test
        public void displayErrorMessage_whenAnUnknownCommandIsTyped() {
            //Given

            String projectCreationCommand = "unknownCommand";

            //When
            taskList.execute(projectCreationCommand);

            //Then
            verify(out).printf(
                    "I don't know what the command \"%s\" is.",
                    "unknownCommand"
            );
        }
    }



}