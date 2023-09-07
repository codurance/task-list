package com.codurance.training.tasks.main;

import com.codurance.training.tasks.TaskList;

public class TaskApplication {
    public static void main(String[] args) {
        try{
            new TaskList().run();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
