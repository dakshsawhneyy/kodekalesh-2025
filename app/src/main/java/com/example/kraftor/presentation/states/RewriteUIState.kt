package com.example.kraftor.presentation.states

import com.example.kraftor.backend.dto.RewriteResponse

data class RewriteUIState(
    val isLoading: Boolean = false,
    val generatedResponse: RewriteResponse? = null,
    val error: String? = null
)
