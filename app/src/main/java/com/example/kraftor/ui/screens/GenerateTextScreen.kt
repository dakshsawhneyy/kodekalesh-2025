package com.example.kraftor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kraftor.ui.components.KraftorDropDown

@Composable
fun GenerateTextScreen() {

    var topic by remember { mutableStateOf("") }
    var selectedTone by remember { mutableStateOf("") }
    var selectedFormat by remember { mutableStateOf("") }


    val isLoading = false
    val generatedText = ""
    val error: String? = null

    val tones = listOf("Professional", "Casual", "Funny", "Friendly", "Formal")
    val formats = listOf("education", "marketing", "blog", "social_media", "public_outreach")

    // Set initial default selection when the screen first launches
    LaunchedEffect(Unit) {
        if (tones.isNotEmpty()) {
            selectedTone = tones[0]
        }
        if (formats.isNotEmpty()) {
            selectedFormat = formats[0]
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Generate Text", style = MaterialTheme.typography.headlineSmall)

        // --- Input Section ---
        OutlinedTextField(
            value = topic,
            onValueChange = { topic = it },
            label = { Text("What should the text be about?") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp), // Make the topic field larger for more text
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Tone Dropdown (Mode 1)
            KraftorDropDown(
                label = "Tone",
                options = tones,
                selectedOption = selectedTone,
                onOptionSelected = { selectedTone = it },
                modifier = Modifier.weight(1f)
            )
            // Format Dropdown (Mode 2)
            KraftorDropDown(
                label = "Format",
                options = formats,
                selectedOption = selectedFormat,
                onOptionSelected = { selectedFormat = it },
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = { /* TODO: Call ViewModel to generate text */ },
            enabled = topic.isNotBlank() && !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generate")
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // --- Result Section ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator()
                }
                error != null -> {
                    Text(
                        text = "Error: $error",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                generatedText.isNotBlank() -> {
                    // Result card with scrollable text
                    OutlinedCard(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = generatedText,
                            modifier = Modifier
                                .padding(16.dp)
                                .verticalScroll(rememberScrollState()) // Make the result scrollable
                        )
                    }
                }
                else -> {
                    // Placeholder text
                    Text(
                        text = "Your generated text will appear here.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Generate Text - Empty")
@Composable
private fun GenerateTextScreenPreview() {
    MaterialTheme {
        GenerateTextScreen()
    }
}
