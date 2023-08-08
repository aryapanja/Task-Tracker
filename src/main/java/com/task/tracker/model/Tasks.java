package com.task.tracker.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Tasks {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // Change the data type to String
    
    @Column(nullable = false) // Add this annotation to ensure title is not null
    private String title;
    
    @Column
    private String description;
    
    @Column
    private Date duedate;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
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
    
    public Date getDuedate() {
        return duedate;
    }
    
    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }
    
    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", description=" + description + ", duedate=" + duedate + "]";
    }
    
    public Tasks(String title, String description, Date duedate) {
        super();
        this.title = title;
        this.description = description;
        this.duedate = duedate;
    }
    
    public Tasks() {
        // TODO Auto-generated constructor stub
    }
}
