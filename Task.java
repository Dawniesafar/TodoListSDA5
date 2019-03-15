package com.company;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Task class represents task details 
 */
public class Task implements Serializable {
    private int taskId;
    private String title;
    private String project;
    private boolean done = false; //Completed! In Progress! Awaiting
    private LocalDate dueDate;

    public Task(){ }
    public Task(int id, String title, String pro, boolean done, LocalDate dueDate){
    this.taskId = id;
    this.title = title;
    this.project = pro;
    this.done = done;
    this.dueDate = dueDate;
    }

    public void setId(int id)
    {
        this.taskId = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setProject(String project)
    {
        this.project = project;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public int getTaskId() {
        return this.taskId;
    }
    public String getTitle()
    {
        return title;
    }
    public String getProject()
    {
        return this.project;
    }
    public LocalDate getDueDate()
    {
        return dueDate;
    }
    public boolean getDone() {
        return done;
    }
    public void markAsDone()
    {
        done = true;
    }

}
