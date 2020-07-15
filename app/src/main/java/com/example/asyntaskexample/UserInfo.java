package com.example.asyntaskexample;

public class UserInfo {
    private String title,task,deadline;
    public UserInfo(String title,String task,String deadline)
    {
        this.title=title;
        this.deadline=deadline;
        this.task=task;
    }
    public UserInfo(String title) {
        this.title=title;

    }
    public String getTask()
    {
        return task;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDeadline()
    {
        return deadline;
    }
}
