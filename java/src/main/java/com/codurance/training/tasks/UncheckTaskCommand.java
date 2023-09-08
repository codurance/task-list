package com.codurance.training.tasks;

class UncheckTaskCommand implements Command {
    private final TaskList taskList;
    private final String idString;

    public UncheckTaskCommand(TaskList taskList, String idString) {
        this.taskList = taskList;
        this.idString = idString;
    }

    @Override
    public void execute() {
        taskList.setDone(idString, false);
    }
}
