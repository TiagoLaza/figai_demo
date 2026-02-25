package com.figai.food.injestion.repository

import com.figai.food.injestion.model.IngredientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface IngredientRepository : JpaRepository<IngredientEntity, Long>, JpaSpecificationExecutor<IngredientEntity>