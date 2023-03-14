package com.codurance.training.tasks;

public class UncheckTaskImp implements TaskActionService{

    @Override
    public void action(Task task) {
        task.setDone(false);
    }
}
