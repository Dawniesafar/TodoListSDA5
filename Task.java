package com.company;

import java.time.LocalDate;

public class Task {
    private int taskId;
    private String title;
    private String project;
    private boolean done; //Completed! In Progress! Awaiting
    private String dueDate;

    public Task(){
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }
    public void setId(int id)
    {
        this.taskId = id;
    }
    public void setProject(String prorject)
    {
        this.project = prorject;
    }
    public void setDone(boolean done){
        this.done = done;
    }
    public int getTaskId()
    {
        return this.taskId;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDueDate()
    {
        return dueDate;
    }
    public String getProject()
    {
        return this.project;
    }
    public boolean getDone()
    {
        return done;
    }
    public void markAsDone()
    {
        done = true;
    }

}
