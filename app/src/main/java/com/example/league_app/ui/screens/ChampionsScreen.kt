package com.example.league_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.league_app.model.MainViewModel
import com.example.league_app.ui.components.ChampionCard
import com.example.league_app.ui.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionsScreen(navController: NavController?, viewModel: MainViewModel) {
    val searchedChampions =
        if (viewModel.searchText.isEmpty()) viewModel.championsCollection
        else viewModel.championsCollection.filter { it.name.contains(viewModel.searchText, true) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Liste des champions") }
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                SearchBar(viewModel)
                Spacer(modifier = Modifier.padding(8.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(searchedChampions.size) {
                        ChampionCard(searchedChampions[it], navController)
                    }
                }
            }
        }
    }
}
