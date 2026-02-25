package com.figai.food.injestion.dto

data class DemoDTO(
    val ingestionResult: List<IngredientDTO> = emptyList(),
    val searchQuery: String,
    val searchResult: List<IngredientDTO> = emptyList(),
    val projectCreationResult: ProjectDTO? = null,
    val projectAddIngredientsResult: ProjectDTO? = null,
    val projectSubmissionResult: ProjectDTO? = null
)