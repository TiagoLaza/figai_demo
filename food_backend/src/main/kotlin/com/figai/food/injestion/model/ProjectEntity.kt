package com.figai.food.injestion.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "project")
data class ProjectEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val requester: String,

    @Column(nullable = false)
    var status: String = "Draft",

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(
        mappedBy = "project",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    val projectIngredients: MutableList<ProjectIngredientEntity> = mutableListOf()
) {
    protected constructor() : this(
        id = 0,
        name = "",
        requester = "",
        status = "Draft",
        createdAt = LocalDateTime.now()
    )

    fun addIngredient(ingredient: IngredientEntity) {
        val pi = ProjectIngredientEntity(project = this, ingredient = ingredient)
        projectIngredients.add(pi)
    }

    fun removeIngredient(ingredient: IngredientEntity) {
        projectIngredients.removeIf { it.ingredient?.id == ingredient.id }
    }
}