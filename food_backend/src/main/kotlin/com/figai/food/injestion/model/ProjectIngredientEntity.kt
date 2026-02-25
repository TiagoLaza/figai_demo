package com.figai.food.injestion.model

import jakarta.persistence.*
import java.io.Serializable

@Embeddable
data class ProjectIngredientId(
    val projectId: Long = 0,
    val ingredientId: Long = 0
) : Serializable

@Entity
@Table(name = "project_ingredient")
data class ProjectIngredientEntity(

    @EmbeddedId
    val id: ProjectIngredientId = ProjectIngredientId(),

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    val project: ProjectEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    val ingredient: IngredientEntity? = null
)