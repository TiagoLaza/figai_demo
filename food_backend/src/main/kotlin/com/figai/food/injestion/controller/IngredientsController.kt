package com.figai.food.injestion.controller

import com.figai.food.injestion.dto.IngredientDTO
import com.figai.food.injestion.service.IngredientService
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/api/ingredient")
class IngredientController(private val service: IngredientService) {

    @GetMapping("/list")
    fun listIngredients(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) listing: String?,
        @RequestParam(required = false) sourcing: String?,
        @RequestParam(required = false) availability: String?,
        @RequestParam(defaultValue = "0") @Min(0) page: Int,
        @RequestParam(defaultValue = "10") @Min(1) @Max(100) size: Int
    ): Page<IngredientDTO> {
        return service.searchIngredients(category, listing, sourcing, availability, page, size)
    }
}