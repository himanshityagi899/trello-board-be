package com.example.trelloboard.Routes.repository;

import com.example.trelloboard.Routes.user.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String projectName);

    @Query("SELECT p.name FROM Project p WHERE p.id = :projectId")
    Optional<String> findNameById(@Param("projectId") Long projectId);
}