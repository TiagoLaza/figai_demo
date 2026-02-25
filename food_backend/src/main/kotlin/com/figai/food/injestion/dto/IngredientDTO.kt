package com.figai.food.injestion.dto

data class IngredientDTO(
    val id: Long,
    val item_id: String,
    val listing: String,
    val category: String?,
    val details: Map<String, String>,
    val certifications: String?,
    val sourcing: String?,
    val pricing: String,
    val availability: String,
    val technical: String?,
    val suggested_use: String?,
    val notes: String?
)