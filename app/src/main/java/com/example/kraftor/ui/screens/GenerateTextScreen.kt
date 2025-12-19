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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kraftor.backend.dto.*
import com.example.kraftor.presentation.viewmodels.GenerateTextViewModel
import com.example.kraftor.ui.components.KraftorDropDown

@Composable
fun GenerateTextScreen(viewModel: GenerateTextViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    var topic by remember { mutableStateOf("") }
    var selectedTone by remember { mutableStateOf("") }
    var selectedFormat by remember { mutableStateOf("") }


    val tones = listOf("professional", "casual", "friendly", "funny", "formal")
    val formats = listOf("blog", "education", "marketing", "public_outreach", "social_media")

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

        OutlinedTextField(
            value = topic,
            onValueChange = { topic = it },
            label = { Text("What should the text be about?") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            KraftorDropDown(
                label = "Tone",
                options = tones,
                selectedOption = selectedTone,
                onOptionSelected = { selectedTone = it },
                modifier = Modifier.weight(1f)
            )

            KraftorDropDown(
                label = "Format",
                options = formats,
                selectedOption = selectedFormat,
                onOptionSelected = { selectedFormat = it },
                modifier = Modifier.weight(1f)
            )
        }

        Button(
            onClick = {

                val request = GenerateTextRequest(
                    category = selectedFormat,
                    topic = topic,
                    tone = selectedTone,
                    language = "english",
                    other_details = ""
                )
                viewModel.generateText(request)
            },
            enabled = topic.isNotBlank() && !uiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generate")
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }
                uiState.error != null -> {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
                uiState.generatedResponse != null -> {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxSize()
                            // Make the entire card scrollable
                            .verticalScroll(rememberScrollState())
                    ) {

                        GeneratedTextResult(response = uiState.generatedResponse!!)
                    }
                }
                else -> {
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

/**
 * A composable that uses `when` to render the UI based on the specific
 * data class that implements the `GenerateTextResponse` sealed interface.
 */
@Composable
private fun GeneratedTextResult(response: GenerateTextResponse) {

    when (response) {
        is BlogResponse -> {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ResultSection(title = "Summary", content = response.summary)
                ResultSection(title = "Outline", items = response.outline)
                ResultSection(title = "Key Points", items = response.key_points)
                ResultSection(title = "Social Captions", items = response.social_captions)
                ResultSection(title = "Hashtags", items = response.hashtags)
            }
        }
        is EducationResponse -> {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ResultSection(title = "Topic", content = response.topic)
                ResultSection(title = "Description", content = response.description)
                ResultSection(title = "Roadmap", items = response.roadmap)
                // You can add more complex UI for Flashcards and MCQs later
                ResultSection(title = "Flashcards", items = response.flashcards)
                ResultSection(title = "Multiple Choice Questions", items = response.MCQs)
            }
        }
        is MarketingResponse -> {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ResultSection(title = "Topic", content = response.topic)
                ResultSection(title = "Description", content = response.long_description)
                ResultSection(title = "Key Points", items = response.key_points)
                ResultSection(title = "Release Plan", items = response.release_plan)
            }
        }
        is PublicOutreachResponse -> {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ResultSection(title = "Topic", content = response.topic)
                ResultSection(title = "Description", content = response.long_description)
                ResultSection(title = "Key Points", items = response.key_points)
            }
        }
        is SocialMediaResponse -> {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ResultSection(title = "Summary", content = response.short_summary)
                ResultSection(title = "Key Points", items = response.key_points)
                ResultSection(title = "Social Captions", items = response.social_captions)
                ResultSection(title = "Hashtags", items = response.hashtags)
            }
        }
        // A safety net in case the API returns an unexpected type
        else -> {
            Text(text = "Unsupported response format received.", modifier = Modifier.padding(16.dp))
        }
    }
}

/**
 * Helper composable to render a section with a title and either a
 * single block of text or a list of items.
 */
@Composable
private fun ResultSection(title: String, content: String? = null, items: List<String>? = null) {
    Column {
        Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Divider(modifier = Modifier.padding(vertical = 4.dp))
        content?.let {
            Text(text = it, style = MaterialTheme.typography.bodyLarge)
        }
        items?.forEach { item ->
            Text(text = "• $item", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
