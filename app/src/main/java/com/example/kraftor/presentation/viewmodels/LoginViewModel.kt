package com.example.kraftor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraftor.backend.dto.LoginRequest
import com.example.kraftor.data.repository.LoginRepo
import com.example.kraftor.presentation.states.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Login screen.
 * @param repository LoginRepo class object
 */
open class LoginViewModel(
    private val repository: LoginRepo? = null
) : ViewModel() {

    // mutable version the ViewModel can change.
    private val _uiState = MutableStateFlow(LoginUIState())

    // read-only version the UI will observe.
    open val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun login(request: LoginRequest) {

        // launch a coroutine that is automatically cancelled
        // when the ViewModel is destroyed, preventing memory leaks.
        viewModelScope.launch {
            // set the loading state
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, loginResponse = null, error = null)
            }

            // call the repository
            val result = repository!!.login(request)

            // update the UI state
            result.onSuccess { response ->
                _uiState.update { it.copy(isLoading = false, loginResponse = response) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, error = error.localizedMessage ?: "Something went wrong. Please try again later") }
            }
        }
    }
}
