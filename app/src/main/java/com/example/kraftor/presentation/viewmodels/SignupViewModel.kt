package com.example.kraftor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraftor.backend.dto.SignupRequest
import com.example.kraftor.data.repository.SignupRepo
import com.example.kraftor.presentation.states.SignupUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Login screen.
 * @param repository LoginRepo class object
 */
class SignupViewModel(
    private val repository: SignupRepo
) : ViewModel() {

    // mutable version the ViewModel can change.
    private val _uiState = MutableStateFlow(SignupUIState())

    // read-only version the UI will observe.
    val uiState = _uiState.asStateFlow()

    fun signup(request: SignupRequest) {

        // launch a coroutine that is automatically cancelled
        // when the ViewModel is destroyed, preventing memory leaks.
        viewModelScope.launch {
            // set the loading state
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, signupResponse = null, error = null)
            }

            // call the repository
            val result = repository.signup(request)

            // update the UI state
            result.onSuccess { response ->
                _uiState.update { it.copy(isLoading = false, signupResponse = response) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, error = error.localizedMessage ?: "Something went wrong. Please try again later") }
            }
        }
    }
}
