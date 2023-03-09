package com.codurance.training.tasks;

public class DeleteTask {
    public DeleteTask(Project project) {
        this.project = project;
    }

    Project project;

    public void deleteTaskFromProject(String taskId){
       // int taskNum = Integer.parseInt(taskId);
        int i=0; boolean f= false;
        for(Task t :  project.getTasks()){
            if(t.getId().equals(taskId)){
                f = true;
                break;
            }
            i++;
        }
        if(f)
            project.getTasks().remove(i);
    }
}
