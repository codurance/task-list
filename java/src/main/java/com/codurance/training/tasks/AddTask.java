package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTask implements AddService{
    @Override
    public boolean add(Map<Long, Project> projectList, String commandLine, PrintWriter out) {
        String[] projectTask = commandLine.split(" ", 3);
        Pattern regex = Pattern.compile("^[a-zA-Z0-9]*$");
        boolean flag = false;

        Matcher matcher = regex.matcher(projectTask[1]);
        boolean matchFound = matcher.find();

        if(!projectTask[1].trim().isEmpty() && matchFound) {
            for (Map.Entry<Long, Project> e : projectList.entrySet()) {
                Project project = e.getValue();
                if(project.getName().equals(projectTask[0])) {
                    project.addTask(new Task(projectTask[1], e.getKey() ,projectTask[2], false));
                    flag = true;
                }
            }
            if(!flag) {
                out.printf("Could not find a project with the name \"%s\".", projectTask[0]);
                out.println();
                return false;
            }
        } else{
            out.println("Make sure the task Id is alpha-numeric and does not contain spaces and special characters.");
            return false;
        }
        return true;
    }
}
