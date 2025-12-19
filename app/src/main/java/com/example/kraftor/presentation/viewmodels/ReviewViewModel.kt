package com.example.kraftor.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraftor.backend.dto.ReviewRequest
import com.example.kraftor.data.repository.ReviewRepo
import com.example.kraftor.presentation.states.ReviewUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Review screen.
 * @param repository Review class object
 */
open class ReviewViewModel(
    private val repository: ReviewRepo? = null
) : ViewModel() {

    // mutable version the ViewModel can change.
    private val _uiState = MutableStateFlow(ReviewUIState())

    // read-only version the UI will observe.
    open val uiState: StateFlow<ReviewUIState> = _uiState.asStateFlow()

    fun giveReview(request: ReviewRequest) {

        // launch a coroutine that is automatically cancelled
        // when the ViewModel is destroyed, preventing memory leaks.
        viewModelScope.launch {
            // set the loading state
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, reviewResponse = null, error = null)
            }

            // call the repository
            val result = repository!!.giveFeedback(request)

            // update the UI state
            result.onSuccess { response ->
                _uiState.update { it.copy(isLoading = false, reviewResponse = response) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, error = error.localizedMessage ?: "Something went wrong. Please try again later") }
            }
        }
    }
}
