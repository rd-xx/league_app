package com.example.league_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.league_app.model.MainViewModel

@Composable
fun SearchBar(viewModel: MainViewModel) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = viewModel.searchText, onValueChange = { viewModel.search(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        placeholder = { Text("Bard") },
        singleLine = true,
    )
}