package com.codurance.training.tasks;

import java.util.ArrayList;
public class AddProject {

    public void add(String name,TaskList taskList) {
        taskList.tasks.put(name, new ArrayList<Task>());
    }
}
