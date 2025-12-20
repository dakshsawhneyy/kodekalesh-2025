package com.example.kraftor.backend.dto

data class SignupRequest(
    val email: String,
    val name: String,
    val password: String
)

data class LoginResponse(
    val message: String,
    val token: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class SignupResponse(
    val message: String,
    val token: String
)