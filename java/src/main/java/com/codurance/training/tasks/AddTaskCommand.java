package com.codurance.training.tasks;

class AddTaskCommand implements Command {
    private final TaskList taskList;
    private final String project;
    private final String description;

    public AddTaskCommand(TaskList taskList, String project, String description) {
        this.taskList = taskList;
        this.project = project;
        this.description = description;
    }

    @Override
    public void execute() {
        taskList.addTask(project, description);
    }
}