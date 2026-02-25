package com.figai.food.injestion.service

import com.figai.food.injestion.dto.ProjectDTO
import com.figai.food.injestion.factory.ProjectDTOFactory
import com.figai.food.injestion.model.*
import com.figai.food.injestion.repository.ProjectRepository
import com.figai.food.injestion.repository.IngredientRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProjectService(
    private val projectRepository: ProjectRepository,
    private val ingredientRepository: IngredientRepository
) {

    fun createProject(name: String, requester: String): ProjectEntity {
        val project = ProjectEntity(name = name, requester = requester)
        return projectRepository.save(project)
    }

    @Transactional
    fun addIngredient(projectId: Long, ingredientId: Long) {
        val project = projectRepository
            .findById(projectId)
            .orElseThrow { RuntimeException("Project not found") }

        val ingredient = ingredientRepository
            .findById(ingredientId)
            .orElseThrow { RuntimeException("Ingredient not found") }

        if (project.projectIngredients.any { it.ingredient?.id == ingredientId })
            return

        project.addIngredient(ingredient)
        projectRepository.save(project)
    }

    @Transactional
    fun removeIngredient(projectId: Long, ingredientId: Long) {
        val project = projectRepository
            .findById(projectId)
            .orElseThrow { RuntimeException("Project not found") }

        project.removeIngredient(ingredientRepository
            .findById(ingredientId)
            .orElseThrow { RuntimeException("Ingredient not found") })

        projectRepository.save(project)
    }

    fun listProjects(page: Int, size: Int): Page<ProjectDTO> {
        val projects = projectRepository.findAll(PageRequest.of(page, size))
        return projects.map { ProjectDTOFactory.fromEntity(it) }
    }

    fun getProject(projectId: Long): ProjectDTO {
        val project = projectRepository
            .findById(projectId)
            .orElseThrow { RuntimeException("Project not found") }
        return ProjectDTOFactory.fromEntity(project)
    }

    @Transactional
    fun submitProject(projectId: Long) {
        val project = projectRepository
            .findById(projectId)
            .orElseThrow { RuntimeException("Project not found") }

        if (project.status != "Draft")
            throw RuntimeException("Project already submitted")

        project.status = "Submitted"
        projectRepository.save(project)
    }
}