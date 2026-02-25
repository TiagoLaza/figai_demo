package com.figai.food.injestion.controller

import com.figai.food.injestion.dto.ProjectDTO
import com.figai.food.injestion.model.ProjectEntity
import com.figai.food.injestion.service.ProjectService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/project")
class ProjectController(private val service: ProjectService) {

    @PostMapping
    fun createProject(@RequestParam name: String, @RequestParam requester: String): ProjectEntity {
        return service.createProject(name, requester)
    }

    @PostMapping("/{projectId}/ingredient/{ingredientId}")
    fun addIngredient(@PathVariable projectId: Long, @PathVariable ingredientId: Long) {
        service.addIngredient(projectId, ingredientId)
    }

    @DeleteMapping("/{projectId}/ingredient/{ingredientId}")
    fun removeIngredient(@PathVariable projectId: Long, @PathVariable ingredientId: Long) {
        service.removeIngredient(projectId, ingredientId)
    }

    @GetMapping
    fun listProjects(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<ProjectDTO> {
        return service.listProjects(page, size)
    }

    @GetMapping("/{projectId}")
    fun getProject(@PathVariable projectId: Long): ProjectDTO {
        return service.getProject(projectId)
    }

    @PostMapping("/{projectId}/submit")
    fun submitProject(@PathVariable projectId: Long) {
        service.submitProject(projectId)
    }
}