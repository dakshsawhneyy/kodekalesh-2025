package com.example.kraftor.presentation.states

import com.example.kraftor.backend.dto.GenerateImageResponse

data class GenerateImageUIState(
    val isLoading: Boolean = false,
    val generatedResponse: GenerateImageResponse? = null,
    val error: String? = null
)
