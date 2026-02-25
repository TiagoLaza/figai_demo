package com.figai.food.injestion.model

import com.fasterxml.jackson.annotation.JsonSetter
import jakarta.persistence.*
import java.time.LocalDateTime
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf

@Entity
@Table(name = "ingredient")
data class IngredientEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val listing: String = "",

    @ElementCollection
    @CollectionTable(
        name = "ingredient_detail",
        joinColumns = [JoinColumn(name = "ingredient_id")]
    )
    @MapKeyColumn(name = "detail")
    @Column(name = "detail_value")
    val details: MutableMap<String, String> = mutableMapOf(),

    @Column
    val certifications: String? = null,

    @Column
    val sourcing: String? = null,

    @Column(nullable = false)
    val pricing: String = "",

    @Column(nullable = false)
    val availability: String = "",

    @Column
    val technical: String? = null,

    @Column
    val notes: String? = null,

    var createdAt: LocalDateTime = LocalDateTime.now()
) {

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "ingredient_category_link",
        joinColumns = [JoinColumn(name = "ingredient_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    var category: MutableSet<IngredientCategoryEntity> = mutableSetOf()

    @JsonSetter("category")
    fun setCategoryFromString(catString: String) {
        category.clear() // clear existing
        category.addAll(catString.split(" & ").map { IngredientCategoryEntity(name = it.trim()) })
    }

    @Column
    var externalId: String = ""

    @JsonSetter("item_id")
    fun setItemId(id: String) {
        this.externalId = id
    }

    @Column
    var suggestedUse: String = ""

    @JsonSetter("suggested_use")
    fun setSuggested(use: String) {
        this.suggestedUse = use
    }

}