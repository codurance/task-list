package com.codurance.training.tasks;

public class CheckTaskImp implements TaskActionService{
    @Override
    public void action(Task task) {
        task.setDone(true);
    }
}
