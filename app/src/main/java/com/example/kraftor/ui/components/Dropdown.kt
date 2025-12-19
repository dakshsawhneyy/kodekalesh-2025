package com.example.kraftor.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp

/**
 * A reusable, styled dropdown menu component for the Kraftor app.
 *
 * @param label The text to display as a label for the dropdown.
 * @param options A list of strings that will populate the dropdown items.
 * @param selectedOption The currently selected string value to display in the text field.
 * @param onOptionSelected A callback function that is invoked with the selected string when the user clicks an item.
 * @param modifier The Modifier to be applied to this component.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KraftorDropDown(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    // This is the main container that manages the dropdown's state (expanded or collapsed).
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }, // Toggles the menu's visibility
        modifier = modifier
    ) {
        // This is the text field part that is always visible.
        OutlinedTextField(
            value = selectedOption, // Displays the currently selected item.
            onValueChange = {}, // Empty because the user cannot type; they must select.
            readOnly = true,
            label = { Text(label) },
            // The icon at the end that changes direction.
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            // This modifier is ESSENTIAL for the menu to appear anchored to the text field.
            modifier = Modifier.menuAnchor()
        )

        // This is the menu itself, which is only visible when 'expanded' is true.
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false } // Closes the menu if the user clicks outside of it.
        ) {
            // Loop through the list of 'options' provided to the function.
            options.forEach { option ->
                // Create a clickable item for each option in the list.
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        // When a user clicks this item:
                        // 1. Execute the callback function, passing the selected 'option' back to the parent screen.
                        onOptionSelected(option)
                        // 2. Close the dropdown menu.
                        expanded = false
                    }
                )
            }
        }
    }
}



@PreviewLightDark
@Composable
private fun KraftorDropDownPreview() {
    // We need to wrap our preview in a theme to provide Material styles.
    // Replace 'YourAppTheme' with the actual name of your theme if different.
    MaterialTheme {
        // Use a Column to show multiple states of the component.
        Column(modifier = Modifier.padding(16.dp)) {
            val options = listOf("Marketing", "Social Media", "Product", "Illustration")
            // State for the preview itself, so we can interact with it.
            var selectedOption by remember { mutableStateOf(options[0]) }

            // --- State 1: A standard, working dropdown ---
            Text("Interactive Dropdown:", style = MaterialTheme.typography.labelLarge)
            KraftorDropDown(
                label = "Category",
                options = options,
                selectedOption = selectedOption,
                onOptionSelected = { selectedOption = it }
            )

            Spacer(Modifier.height(32.dp))

            // --- State 2: A dropdown with a very long option name ---
            Text("With Long Text:", style = MaterialTheme.typography.labelLarge)
            KraftorDropDown(
                label = "Purpose",
                options = listOf("Quick Advertisement", "Detailed Product Illustration", "Icon for a Mobile App"),
                selectedOption = "Detailed Product Illustration",
                onOptionSelected = {}
            )
        }
    }
}