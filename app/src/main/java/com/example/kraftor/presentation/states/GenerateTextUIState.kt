package com.example.kraftor.presentation.states

import com.example.kraftor.backend.dto.GenerateTextResponse

data class GenerateTextUIState(
    val isLoading: Boolean = false,
    val generatedResponse: GenerateTextResponse? = null,
    val error: String? = null
)