package com.figai.food.injestion.factory

import com.figai.food.injestion.model.IngredientEntity
import com.figai.food.injestion.dto.IngredientDTO

object IngredientDTOFactory {
    fun fromEntity(entity: IngredientEntity): IngredientDTO {
        val categoryString = if (entity.category.isNotEmpty()) {
            entity.category.map { it.name }.sorted().joinToString(" & ")
        } else null

        return IngredientDTO(
            id = entity.id,
            item_id = entity.externalId,
            listing = entity.listing,
            category = categoryString,
            details = entity.details,
            certifications = entity.certifications,
            sourcing = entity.sourcing,
            pricing = entity.pricing,
            availability = entity.availability,
            technical = entity.technical,
            suggested_use = entity.suggestedUse,
            notes = entity.notes
        )
    }
}