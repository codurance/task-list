package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ShowFactory {

    public Show get(String commandRest){

        Show show = null;

        switch (commandRest){
            case "by project":
                show = new ShowTasks();
                break;
            case "by deadline":
                show = new ShowTaskWithDeadlines();
                break;
            default:
                break;
        }

        return show;
    }
}
