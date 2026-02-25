package com.figai.food.injestion.repository

import com.figai.food.injestion.model.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<ProjectEntity, Long>