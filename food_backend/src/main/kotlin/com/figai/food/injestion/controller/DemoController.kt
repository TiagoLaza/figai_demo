package com.figai.food.injestion.controller

import com.figai.food.injestion.dto.DemoDTO
import com.figai.food.injestion.dto.IngredientDTO
import com.figai.food.injestion.factory.ProjectDTOFactory
import com.figai.food.injestion.factory.IngredientDTOFactory
import com.figai.food.injestion.model.IngredientEntity
import com.figai.food.injestion.service.IngredientService
import com.figai.food.injestion.service.ProjectService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource

@RestController
@RequestMapping("/api/test")
class DemoController(
    private val ingredientService: IngredientService,
    private val projectService: ProjectService
) {

    @PostMapping("/demo")
    fun demoWorkflow(): DemoDTO {
        val mapper = jacksonObjectMapper()

        // Ingest JSON
        val resource = ClassPathResource("products.json")
        val ingredients: List<IngredientEntity> = mapper.readValue(resource.inputStream)
        val ingested = ingredients.map { ingredientService.saveIngredient(it) }
        val ingestionResult = ingested.map { IngredientDTOFactory.fromEntity(it) }


        // Demonstrate search / filters
        // Example: search category = "Fruits", listing ILIKE "Apple", availability "In stock"
        val searchPage = ingredientService.searchIngredients(
            category = "Fruits",
            listing = null,
            sourcing = null,
            availability = "In stock",
            page = 0,
            size = 10
        )
        val searchResult: List<IngredientDTO> = searchPage.content


        // Create a project
        val project = projectService.createProject("Demo Project", "Demo User")
        val projectCreationResult = ProjectDTOFactory.fromEntity(project)


        // Add ingredients to the project
        ingested.take(3).forEach { ingredient ->
            projectService.addIngredient(project.id, ingredient.id)
        }
        val projectWithIngredients = projectService.getProject(project.id)
        val projectAddIngredientsResult = projectWithIngredients


        // Submit the project
        projectService.submitProject(project.id)
        val projectSubmitted = projectService.getProject(project.id)
        val projectSubmissionResult = projectSubmitted


        // Combine into DTO
        return DemoDTO(
            ingestionResult = ingestionResult,
            searchQuery = "category = \"Fruits\", listing ILIKE \"Apple\", availability = \"In stock\"",
            searchResult = searchResult,
            projectCreationResult = projectCreationResult,
            projectAddIngredientsResult = projectAddIngredientsResult,
            projectSubmissionResult = projectSubmissionResult
        )
    }
}