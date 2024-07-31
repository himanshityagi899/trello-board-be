package com.example.trelloboard.Routes.service;

import com.example.trelloboard.Routes.dto.TaskDto;
import com.example.trelloboard.Routes.enumc.TaskStatus;
import com.example.trelloboard.Routes.repository.ProjectRepository;
import com.example.trelloboard.Routes.repository.TaskRepository;
import com.example.trelloboard.Routes.repository.UserRepository;
import com.example.trelloboard.Routes.user.Project;
import com.example.trelloboard.Routes.user.Task;
import com.example.trelloboard.Routes.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> createTask(TaskDto taskDto) {
        // Fetch project by name
        Optional<Project> project = projectRepository.findByName(taskDto.getProjectName());

        // Fetch users by email
        List<User> assignedUsers = userRepository.findByEmail(taskDto.getAssignedUserEmail());

        if (project.isPresent() && !assignedUsers.isEmpty()) {
            Task task = new Task();
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setStatus(TaskStatus.valueOf(taskDto.getStatus().toUpperCase())); // Ensure case consistency
            task.setDueDate(taskDto.getDueDate());
            task.setTags(taskDto.getTags());
            task.setAssignedUser(assignedUsers.get(0)); // Use the first user from the list
            task.setProject(project.get());
            taskRepository.save(task);
            return Optional.of(task);
        } else {
            return Optional.empty(); // Return an empty optional if project is not found or no users are found
        }
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public List<TaskDto> findTasksWithProjectNames() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> {
                    String projectName = projectRepository.findById(task.getProject().getId())
                            .map(project -> project.getName())
                            .orElse("Unknown");

                    return new TaskDto(
                            task.getId(),
                            task.getName(),
                            task.getDescription(),
                            task.getStatus().toString(), // Convert TaskStatus to String
                            task.getDueDate(),
                            task.getTags(),
                            task.getProject() != null ? task.getProject().getId() : null, // Project ID
                            projectName
                    );
                })
                .collect(Collectors.toList());
    }


    public Task updateTaskStatus(Long taskId, String newStatus) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(TaskStatus.valueOf(newStatus));
            return taskRepository.save(task);
        }
        return null;
    }

}