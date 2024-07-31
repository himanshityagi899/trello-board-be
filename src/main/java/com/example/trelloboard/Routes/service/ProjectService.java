package com.example.trelloboard.Routes.service;

import com.example.trelloboard.Routes.dto.ProjectDto;
import com.example.trelloboard.Routes.repository.ProjectRepository;
import com.example.trelloboard.Routes.user.Project;
import com.example.trelloboard.Routes.user.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.trelloboard.Routes.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(project -> new ProjectDto(project.getId(), project.getName(), project.getDescription()))
                .collect(Collectors.toList());
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }




    public Project updateProject(Long id, Project project) {
        if (projectRepository.existsById(id))  {
            project.setId(id);
            return projectRepository.save(project);
        } else {
            return null;
        }
    }

    public List<Task> getTasksByProjectId(Long id) {
        return taskRepository.findByProjectId(id);
    }
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Optional<String> getProjectNameById(Long projectId) {
        return projectRepository.findNameById(projectId);
    }
}