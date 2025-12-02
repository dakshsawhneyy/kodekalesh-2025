package com.example.kraftor.backend.dto

import kotlinx.serialization.Serializable

data class GenerateTextRequest(
    val category: String,
    val topic: String,
    val tone: String,
    val language: String,
    val other_details: String
)



sealed interface GenerateTextResponse

    data class BlogResponse(
        val outline: List<String>,
        val long_description: List<String>,
        val summary: String,
        val social_captions: List<String>,
        val hashtags: List<String>,
        val key_points: List<String>
    ) : GenerateTextResponse

    data class EducationResponse(
        val topic: String,
        val description: String,
        val roadmap: List<String>,
        val flashcards: List<String>,
        val MCQs: List<String>,
        val Answers: List<String>
    ) : GenerateTextResponse

    data class MarketingResponse(
        val topic: String,
        val long_description: String,
        val key_points: List<String>,
        val release_plan: List<String>
    ) : GenerateTextResponse

    data class PublicOutreachResponse(
        val topic: String,
        val long_description: String,
        val key_points: List<String>
    ) : GenerateTextResponse

    data class SocialMediaResponse(
        val short_summary: String,
        val long_description: String,
        val social_captions: List<String>,
        val hashtags: List<String>,
        val key_points: List<String>
    ) : GenerateTextResponse