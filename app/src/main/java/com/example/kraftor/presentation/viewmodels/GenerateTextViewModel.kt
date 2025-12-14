package com.example.kraftor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraftor.backend.dto.GenerateTextRequest
import com.example.kraftor.data.repository.GenerateTextRepo
import com.example.kraftor.presentation.states.GenerateTextUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Generate Text screen.
 * @param repository GenerateTextRepo class object
 */
class GenerateTextViewModel(
    private val repository: GenerateTextRepo
) : ViewModel() {

    // mutable version the ViewModel can change.
    private val _uiState = MutableStateFlow(GenerateTextUIState())

    // read-only version the UI will observe.
    val uiState = _uiState.asStateFlow()

    fun generateText(request: GenerateTextRequest) {

        // launch a coroutine that is automatically cancelled
        // when the ViewModel is destroyed, preventing memory leaks.
        viewModelScope.launch {
            // set the loading state
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, generatedResponse = null, error = null)
            }

            // call the repository
            val result = repository.generateText(request)

            // update the UI state
            result.onSuccess { response ->
                _uiState.update { it.copy(isLoading = false, generatedResponse = response) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, error = error.localizedMessage ?: "Something went wrong. Please try again later") }
            }
        }
    }
}
