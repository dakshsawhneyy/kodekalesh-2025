package com.example.kraftor.presentation.states

import com.example.kraftor.backend.dto.SignupResponse

data class SignupUIState(
    val isLoading: Boolean = false,
    val signupResponse: SignupResponse? = null,
    val error: String? = null
)