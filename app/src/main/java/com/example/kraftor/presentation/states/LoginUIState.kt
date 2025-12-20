package com.example.kraftor.presentation.states

import com.example.kraftor.backend.dto.LoginResponse

data class LoginUIState(
    val isLoading: Boolean = false,
    val loginResponse: LoginResponse? = null,
    val error: String? = null
)
