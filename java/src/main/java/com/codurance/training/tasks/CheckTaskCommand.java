package com.codurance.training.tasks;

class CheckTaskCommand implements Command {
    private final TaskList taskList;
    private final String idString;

    public CheckTaskCommand(TaskList taskList, String idString) {
        this.taskList = taskList;
        this.idString = idString;
    }

    @Override
    public void execute() {
        taskList.setDone(idString, true);
    }
}
