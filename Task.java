package com.company;
import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private int taskId;
    private String title;
    private String project;
    private boolean done = false; //Completed! In Progress! Awaiting
    private LocalDate dueDate;

    public Task(){ }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDueDate(LocalDate dueDate) {
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
    public int getTaskId() {
        return this.taskId;
    }
    public String getTitle()
    {
        return title;
    }
    public LocalDate getDueDate()
    {
        return dueDate;
    }
    public String getProject()
    {
        return this.project;
    }
    public boolean getDone() {
        return done;
    }
    public void markAsDone()
    {
        done = true;
    }

}
