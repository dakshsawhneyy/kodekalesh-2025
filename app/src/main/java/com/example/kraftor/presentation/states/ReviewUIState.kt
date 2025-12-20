package com.example.kraftor.presentation.states

import com.example.kraftor.backend.dto.ReviewResponse

data class ReviewUIState(
    val isLoading: Boolean = false,
    val reviewResponse: ReviewResponse? = null,
    val error: String? = null
)
