package com.codurance.training.tasks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class TaskListTest {
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private final PrintWriter out = new PrintWriter(System.out);

    private final TaskList taskList = new TaskList(in, out);

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
    }

}