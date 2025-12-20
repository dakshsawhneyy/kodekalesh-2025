package com.example.kraftor.backend.dto

data class GenerateImageRequest(
    val category: String,
    val topic: String,
    val purpose: String
)

data class GenerateImageResponse(
    val imageURL: String
)
