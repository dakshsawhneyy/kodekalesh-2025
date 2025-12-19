package com.example.kraftor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraftor.backend.dto.GenerateImageRequest
import com.example.kraftor.data.repository.GenerateImageRepo
import com.example.kraftor.presentation.states.GenerateImageUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Generate Image screen.
 * @param repository GenerateImageRepo class object
 */
open class GenerateImageViewModel(
    private val repository: GenerateImageRepo? = null
) : ViewModel() {

    // mutable version the ViewModel can change.
    private val _uiState = MutableStateFlow(GenerateImageUIState())

    // read-only version the UI will observe.
    open val uiState: StateFlow<GenerateImageUIState> = _uiState.asStateFlow()

    fun generateImage(request: GenerateImageRequest) {

        // launch a coroutine that is automatically cancelled
        // when the ViewModel is destroyed, preventing memory leaks.
        viewModelScope.launch {
            // set the loading state
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, generatedResponse = null, error = null)
            }

            // call the repository
            val result = repository!!.generateImage(request)

            // update the UI state
            result.onSuccess { response ->
                _uiState.update { it.copy(isLoading = false, generatedResponse = response) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, error = error.localizedMessage ?: "Something went wrong. Please try again later") }
            }
        }
    }
}
