package com.example.trelloboard.Routes.controller;

import com.example.trelloboard.Routes.dto.TaskDto;
import com.example.trelloboard.Routes.dto.TaskStatusUpdateRequest;
import com.example.trelloboard.Routes.enumc.TaskStatus;

import com.example.trelloboard.Routes.repository.TaskRepository;
import com.example.trelloboard.Routes.service.TaskService;
import com.example.trelloboard.Routes.user.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"https://trello-board-fe.vercel.app","http://localhost:3000"})
@RestController
@RequestMapping("/api/tasks")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskDto) {
        Optional<Task> task = taskService.createTask(taskDto);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/with-projects")
    public List<TaskDto> getTasks() {
        return taskService.findTasksWithProjectNames();
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> updateTaskStatus(
            @PathVariable Long taskId,
            @RequestBody Map<String, String> updates) {

        String newStatus = updates.get("status");
        Task updatedTask = taskService.updateTaskStatus(taskId, newStatus);

        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}