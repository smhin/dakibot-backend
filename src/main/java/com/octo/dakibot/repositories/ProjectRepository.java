package com.octo.dakibot.repositories;

import com.octo.dakibot.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {

}
