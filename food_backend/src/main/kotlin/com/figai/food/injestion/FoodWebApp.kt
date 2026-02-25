package com.figai.food.injestion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodWebApp

fun main(args: Array<String>) {
	runApplication<FoodWebApp>(*args)
}
