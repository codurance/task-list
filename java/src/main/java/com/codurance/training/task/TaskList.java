package com.codurance.training.task;

import com.codurance.training.error.Error;
import com.codurance.training.helper.Helper;
import com.codurance.training.operations.customize.CustomizeId;
import com.codurance.training.operations.delete.DeleteId;
import com.codurance.training.operations.manage.ManageDeadline;
import com.codurance.training.operations.manage.ManageTask;
import com.codurance.training.operations.showprojects.ShowProjects;
import com.codurance.training.operations.adder.Adder;
import com.codurance.training.operations.showprojects.TodayProjects;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskList implements TaskListInterface{
    private long lastId=0;
    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final Adder adder=new Adder();
    private final ShowProjects showProjects=new ShowProjects();
    private final TodayProjects todayProjects =new TodayProjects();
    private final ManageTask manageTask=new ManageTask();
    private final ManageDeadline manageDeadline=new ManageDeadline();
    private final Helper helper=new Helper();
    private final Error error=new Error();
    private final CustomizeId customizeId=new CustomizeId();
    private final DeleteId deleteId=new DeleteId();

    public void showTaskList(PrintWriter out,String commandLine){
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[1];
        switch (subcommand) {
            case "project":
                showProjects.byProject(out, tasks);
                break;
            case "date":
                showProjects.byDate(out,tasks);
                break;
            case "deadline":
                showProjects.byDeadline(out,tasks);
                break;
            default:
                out.println("entered wrong command");
                break;
        }
    }

    public void add(PrintWriter out,String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            adder.addProject(subcommandRest[1],tasks);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            adder.addTask(out,++lastId,projectTask[0], projectTask[1],tasks);
        }
    }

    public void manageTask(PrintWriter out,String commandLine,boolean status){
        manageTask.manage(out,commandLine,status,tasks);
    }

    public void manageDeadline(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        manageDeadline.manage(subcommandRest[0],subcommandRest[1],tasks);
    }

    @Override
    public void showTodayProjects(PrintWriter out) {
        todayProjects.show(out,tasks);
    }

    public void help(PrintWriter out) {
        helper.help(out);
    }

    public void error(PrintWriter out, String command) {
        error.showError(out,command);
    }

    @Override
    public void customizeId(PrintWriter out,String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        customizeId.customize(out,subcommandRest[0],subcommandRest[1],tasks);
    }

    @Override
    public void deleteId(String id) {
        deleteId.delete(id,tasks);
    }
}
