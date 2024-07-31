package com.example.trelloboard.Routes.controller;

import com.example.trelloboard.Routes.dto.ProjectDto;
import com.example.trelloboard.Routes.service.ProjectService;
import com.example.trelloboard.Routes.user.Project;
import com.example.trelloboard.Routes.user.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"https://trello-board-fe.vercel.app","http://localhost:3000"})
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAllProjects();
        logger.info("Projects: {}", projects);
        return ResponseEntity.ok(projects);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        try {
            if (project == null || project.getName() == null || project.getName().isEmpty()) {
                return ResponseEntity.badRequest().body("Project name is required.");
            }
            Project createdProject = projectService.createProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
        } catch (Exception e) {
            logger.error("Error creating project:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating project.");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(id, project);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/list")
//    public List<ProjectDto> getAllProjects() {
//        return projectService.getAllProjects().stream()
//                .map(project -> new ProjectDto(project.getId(), project.getName(), project.getDescription()))
//                .collect(Collectors.toList());
//    }


    @GetMapping("/{id}/tasks")
    public List<Task> getTasksByProjectId(@PathVariable Long id) {
        return projectService.getTasksByProjectId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getProjectNameById(@PathVariable Long id) {
        Optional<String> projectName = projectService.getProjectNameById(id);
        return projectName.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found"));
    }
}