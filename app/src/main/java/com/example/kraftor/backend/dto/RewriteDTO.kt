package com.example.kraftor.backend.dto

data class RewriteRequest(
    val text: String,
    val platform: String
)

data class RewriteResponse(
    val rewritten: String
)
