package com.figai.food.injestion.specification

import com.figai.food.injestion.model.IngredientEntity
import jakarta.persistence.criteria.JoinType
import org.springframework.data.jpa.domain.Specification

object IngredientSpecifications {
    fun hasCategory(categoryName: String?) = Specification<IngredientEntity> { root, _, cb ->
        if (categoryName.isNullOrBlank())
            null
        else {
            val join = root.joinSet<IngredientEntity, Any>("category")
            cb.equal(join.get<String>("name"), categoryName)
        }
    }

    fun listingContains(listing: String?) = Specification<IngredientEntity> { root, _, cb ->
        if (listing.isNullOrBlank()) null
        else cb.like(cb.lower(root.get("listing")), "%${listing.lowercase()}%")
    }

    fun sourcingContains(sourcing: String?) = Specification<IngredientEntity> { root, _, cb ->
        if (sourcing.isNullOrBlank()) null
        else cb.like(cb.lower(root.get("sourcing")), "%${sourcing.lowercase()}%")
    }

    fun hasAvailability(availability: String?) = Specification<IngredientEntity> { root, _, cb ->
        if (availability.isNullOrBlank()) null
        else cb.equal(root.get<String>("availability"), availability)
    }
}