package com.example.trelloboard.Routes.user;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.trelloboard.Routes.enumc.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private Date dueDate;

    @ElementCollection
    private List<String> tags;

    @ManyToOne
    @JsonBackReference
    private User assignedUser;

//    @ManyToOne
//    @JoinColumn(name = "project_id")
//    @JsonBackReference
//    private Project project;


//    @ManyToOne
//    @JoinColumn(name = "project_id", nullable = false)
//    private Project project;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference("project-tasks")
    private Project project;

}