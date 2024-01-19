package com.example.league_app.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.league_app.model.MainViewModel

@Composable
fun ChampionsDrawer(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    LaunchedEffect("") {
        viewModel.load()
    }

    if (viewModel.isLoading.value) {
        Text(text = "Chargement...")
        return
    }

    if (viewModel.errorMessage.value.isNotEmpty()) {
        Text(text = viewModel.errorMessage.value)
        return
    }

    Text("Champions", modifier = Modifier.padding(16.dp))
    Divider()
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(viewModel.champions.size) {
            NavigationDrawerItem(
                label = { Text(text = viewModel.champions[it].name) },
                selected = false,
                onClick = { /*TODO*/ })
        }
    }
}
