package com.figai.food.injestion.dto

import java.time.LocalDateTime

data class ProjectDTO(
    val id: Long,
    val name: String,
    val requester: String,
    val status: String,
    val createdAt: LocalDateTime,
    val ingredients: List<IngredientDTO> = emptyList()
)