package com.example.kraftor.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kraftor.R
import com.example.kraftor.backend.dto.GenerateImageRequest
import com.example.kraftor.backend.dto.GenerateImageResponse
import com.example.kraftor.presentation.states.GenerateImageUIState
import com.example.kraftor.presentation.viewmodels.GenerateImageViewModel
import com.example.kraftor.ui.components.KraftorDropDown
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun GenerateImageScreen(viewModel: GenerateImageViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    // State for UI inputs
    var topic by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var selectedPurpose by remember { mutableStateOf("") }

    val categories = listOf("poster", "logo", "banner", "thumbnail", "ad")
    val purposes = listOf("social_media","branding", "entertainment", "education", "marketing")

    // Set default selection when the screen first launches
    LaunchedEffect(Unit) {
        if (categories.isNotEmpty()) {
            selectedCategory = categories[0]
        }
        if (purposes.isNotEmpty()) {
            selectedPurpose = purposes[0]
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Generate Image", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = topic,
            onValueChange = { topic = it },
            label = { Text("Image Description or Topic") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            KraftorDropDown(
                label = "Category",
                options = categories,
                selectedOption = selectedCategory,
                onOptionSelected = { selectedCategory = it },
                modifier = Modifier.weight(1f)
            )
            KraftorDropDown(
                label = "Purpose",
                options = purposes,
                selectedOption = selectedPurpose,
                onOptionSelected = { selectedPurpose = it },
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = {
                val request = GenerateImageRequest(
                    topic = topic,
                    category = selectedCategory,
                    purpose = selectedPurpose
                )
                viewModel.generateImage(request)
            },
            enabled = topic.isNotBlank() && !uiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generate")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            //loading indicator
            if (uiState.isLoading) {
                CircularProgressIndicator()
            }

            //error message if it exists
            uiState.error?.let { errorMsg ->
                Text(
                    text = "Error: $errorMsg",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }

            //result if it exists and not loading
            val response = uiState.generatedResponse
            if (response != null && !uiState.isLoading) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = 16.dp)
                ) {
                    //imageUrl from the response object
                    AsyncImage(
                        model = response.imageURL,
                        contentDescription = "Generated Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentScale = ContentScale.Fit
                    )
                    OutlinedButton(
                        onClick = { /* TODO: Show feedback dialog or screen */ },
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "Give Feedback"
                            )
                            Text("Feedback")
                        }
                    }
                }
            }
        }
    }
}


private class FakeGenerateImageViewModel(initialState: GenerateImageUIState) : GenerateImageViewModel() {
    override val uiState: StateFlow<GenerateImageUIState> = MutableStateFlow(initialState)
}

@Preview(name = "Empty - Light", showBackground = true, widthDp = 360)
@Preview(name = "Empty - Dark", showBackground = true, widthDp = 360)
@Composable
private fun GenerateImageScreenEmptyPreview() {
    val fakeViewModel = FakeGenerateImageViewModel(initialState = GenerateImageUIState())
    // Use your app's actual theme if it's named differently
    MaterialTheme {
        GenerateImageScreen(viewModel = fakeViewModel)
    }
}

@Preview(name = "Result", showBackground = true, widthDp = 360)
@Composable
private fun GenerateImageScreenResultPreview() {
    val fakeViewModel = FakeGenerateImageViewModel(
        initialState = GenerateImageUIState(
            isLoading = false,
            generatedResponse = GenerateImageResponse(
                // Use a placeholder image service for previews
                imageURL = "https://picsum.photos/seed/kraftor_preview/400/225"
            )
        )
    )
    MaterialTheme {
        GenerateImageScreen(viewModel = fakeViewModel)
    }
}

@Preview(name = "Loading", showBackground = true, widthDp = 360)
@Composable
private fun GenerateImageScreenLoadingPreview() {
    val fakeViewModel = FakeGenerateImageViewModel(
        initialState = GenerateImageUIState(isLoading = true)
    )
    MaterialTheme {
        GenerateImageScreen(viewModel = fakeViewModel)
    }
}

@Preview(name = "Error", showBackground = true, widthDp = 360)
@Composable
private fun GenerateImageScreenErrorPreview() {
    val fakeViewModel = FakeGenerateImageViewModel(
        initialState = GenerateImageUIState(error = "Network connection timed out.")
    )
    MaterialTheme {
        GenerateImageScreen(viewModel = fakeViewModel)
    }
}