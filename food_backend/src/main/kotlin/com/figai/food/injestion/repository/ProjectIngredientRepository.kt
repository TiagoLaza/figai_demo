package com.figai.food.injestion.repository

import com.figai.food.injestion.model.ProjectIngredientEntity
import com.figai.food.injestion.model.ProjectIngredientId
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectIngredientRepository : JpaRepository<ProjectIngredientEntity, ProjectIngredientId> {
    fun findByProjectId(projectId: Long): List<ProjectIngredientEntity>
}