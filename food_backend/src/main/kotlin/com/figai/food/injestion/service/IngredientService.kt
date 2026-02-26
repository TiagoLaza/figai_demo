package com.figai.food.injestion.service

import com.figai.food.injestion.dto.IngredientDTO
import com.figai.food.injestion.factory.IngredientDTOFactory
import com.figai.food.injestion.model.IngredientEntity
import com.figai.food.injestion.repository.IngredientRepository
import com.figai.food.injestion.specification.IngredientSpecifications
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class IngredientService(private val repository: IngredientRepository) {

    fun searchIngredients(
        category: String?,
        listing: String?,
        sourcing: String?,
        availability: String?,
        page: Int,
        size: Int
    ): Page<IngredientDTO> {
        val spec: Specification<IngredientEntity> = Specification.where(
            IngredientSpecifications.hasCategory(category)
        ).and(IngredientSpecifications.listingContains(listing))
            .and(IngredientSpecifications.sourcingContains(sourcing))
            .and(IngredientSpecifications.hasAvailability(availability))

        val pageable = PageRequest.of(page, size)

        return repository.findAll(spec, pageable)
            .map { IngredientDTOFactory.fromEntity(it) }
    }

    fun saveIngredient(ingredient: IngredientEntity): IngredientEntity {
        return repository.save(ingredient)
    }
}