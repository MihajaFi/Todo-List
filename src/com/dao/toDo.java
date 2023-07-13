package com.dao;

import java.sql.Timestamp;

public class toDo {
private int id  ;
private String title ;
private String description ;
private Timestamp deadline ;
private int priority ;
private  boolean done ;

    public toDo(int id, String title, String description, Timestamp deadline, int priority, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String status = done ? "✓" : "✗";
        String priorityText = getPriorityText();

        StringBuilder sb = new StringBuilder();
        sb.append("ToDo Details:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Title: ").append(title).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Deadline: ").append(deadline).append("\n");
        sb.append("Priority: ").append(priorityText).append("\n");
        sb.append("Status: ").append(status).append("\n");

        return sb.toString();
    }

    private String getPriorityText() {
        switch (priority) {
            case 1:
                return "Low";
            case 5:
                return "Medium";
            case 10:
                return "High";
            default:
                return "Unknown";
        }
    }

}
