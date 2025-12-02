package com.example.kraftor.backend.dto

data class ReviewRequest(
    val name: String,
    val email: String,
    val rating: String,
    val review_text: String
)

data class ReviewResponse(
    val message: String,
    val reviewId: String
)
