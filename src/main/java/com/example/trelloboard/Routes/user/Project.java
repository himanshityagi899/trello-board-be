package com.example.trelloboard.Routes.user;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.List;


@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;


//    @OneToMany(mappedBy = "project")
//    private List<Task> tasks;


//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Task> tasks;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonManagedReference("project-tasks")
    private List<Task> tasks;


}