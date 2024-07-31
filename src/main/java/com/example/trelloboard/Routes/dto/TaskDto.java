package com.example.trelloboard.Routes.dto;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Date;
//import java.util.List;
//
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class TaskDto {
//    private Long id;
//    private String name;
//    private String description;
//    private String status;
//    private Date dueDate;
//    private List<String> tags;
//    private Long assignedUserId;
//    private Long projectId;
//    private String projectName;
//    private String assignedUserEmail;
    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public Date getDueDate() {
//        return dueDate;
//    }
//
//    public void setDueDate(Date dueDate) {
//        this.dueDate = dueDate;
//    }
//
//    public List<String> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<String> tags) {
//        this.tags = tags;
//    }
//
//    public Long getAssignedUserId() {
//        return assignedUserId;
//    }
//
//    public void setAssignedUserId(Long assignedUserId) {
//        this.assignedUserId = assignedUserId;
//    }
//
//    public Long getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(Long projectId) {
//        this.projectId = projectId;
//    }
//
//    public String getProjectName(){return projectName;}
//
//    public void setProjectName(String projectName) {
//        this.projectName = projectName;
//    }
//
//


//}

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private String status;
    private Date dueDate;
    private List<String> tags;
    private Long assignedUserId;
    private Long projectId;
    private String projectName;
    private String assignedUserEmail;

//    public TaskDto(Long id, String name, String description, String string, Date dueDate, List<String> tags, Long aLong, String projectName) {
//    }

    public TaskDto(Long id, String name, String description, String status, Date dueDate, List<String> tags, Long projectId, String projectName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.tags = tags;
        this.projectId = projectId;
        this.projectName = projectName;
    }
}

