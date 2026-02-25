package com.figai.food.injestion.factory


import com.figai.food.injestion.dto.ProjectDTO
import com.figai.food.injestion.dto.IngredientDTO
import com.figai.food.injestion.model.ProjectEntity

object ProjectDTOFactory {
    fun fromEntity(project: ProjectEntity): ProjectDTO {
        val ingredientDTOs: List<IngredientDTO> = project.projectIngredients
            .mapNotNull { it.ingredient }
            .map { IngredientDTOFactory.fromEntity(it) }

        return ProjectDTO(
            id = project.id,
            name = project.name,
            requester = project.requester,
            status = project.status,
            createdAt = project.createdAt,
            ingredients = ingredientDTOs
        )
    }
}