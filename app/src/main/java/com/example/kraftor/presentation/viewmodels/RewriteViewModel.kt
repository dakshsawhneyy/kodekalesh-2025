package com.example.kraftor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraftor.backend.dto.RewriteRequest
import com.example.kraftor.data.repository.RewriteTextRepo
import com.example.kraftor.presentation.states.RewriteUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Rewrite screen.
 * @param repository Rewrite class object
 */
class RewriteViewModel(
    private val repository: RewriteTextRepo
) : ViewModel() {

    // mutable version the ViewModel can change.
    private val _uiState = MutableStateFlow(RewriteUIState())

    // read-only version the UI will observe.
    val uiState = _uiState.asStateFlow()

    fun rewrite(request: RewriteRequest) {

        // launch a coroutine that is automatically cancelled
        // when the ViewModel is destroyed, preventing memory leaks.
        viewModelScope.launch {
            // set the loading state
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, generatedResponse = null, error = null)
            }

            // call the repository
            val result = repository.rewriteText(request)

            // update the UI state
            result.onSuccess { response ->
                _uiState.update { it.copy(isLoading = false, generatedResponse = response) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, error = error.localizedMessage ?: "Something went wrong. Please try again later") }
            }
        }
    }
}
