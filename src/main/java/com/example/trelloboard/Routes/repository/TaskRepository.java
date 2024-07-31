package com.example.trelloboard.Routes.repository;

import com.example.trelloboard.Routes.enumc.TaskStatus;
import com.example.trelloboard.Routes.user.Project;
import com.example.trelloboard.Routes.user.Task;
import com.example.trelloboard.Routes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);

    List<Task> findByProjectId(Long projectId);

    List<Task> findAll();

    @Query("SELECT t, p.name AS projectName FROM Task t JOIN t.project p")
    List<Object[]> findTasksWithProjectNames();


    @Query("SELECT t FROM Task t JOIN t.project p WHERE t.project.id = :projectId")
    List<Task> findTasksByProjectId(@Param("projectId") Long projectId);
}


