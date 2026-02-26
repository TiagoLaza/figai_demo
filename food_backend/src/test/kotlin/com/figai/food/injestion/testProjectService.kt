package com.figai.food.injestion

import com.figai.food.injestion.controller.ProjectController
import com.figai.food.injestion.repository.ProjectRepository
import com.figai.food.injestion.service.ProjectService
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ProjectController::class)
class testProjectService {

	@Autowired
	lateinit var mockMvc: MockMvc

	@MockBean
	lateinit var projectService: ProjectService

	@MockBean
	lateinit var projectRepository: ProjectRepository

	@Test
	fun shouldReturnProjectNotFound() {
		whenever(projectRepository.findById(1L))
			.thenReturn(null)

		mockMvc.perform(post("/1/ingredient/1"))
			.andExpect(status().isNotFound)
	}
}
