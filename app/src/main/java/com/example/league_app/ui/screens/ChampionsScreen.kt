package com.example.league_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.league_app.R
import com.example.league_app.model.MainViewModel
import com.example.league_app.ui.components.SearchBar
import com.example.league_app.ui.components.champions.ChampionCard
import com.example.league_app.ui.components.layout.Layout

@Composable
fun ChampionsScreen(navController: NavController, viewModel: MainViewModel) {
    val searchedChampions =
        if (viewModel.searchText.isEmpty()) viewModel.championsCollection
        else viewModel.championsCollection.filter { it.name.contains(viewModel.searchText, true) }

    Layout(navController, stringResource(R.string.title_champions_list)) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SearchBar(viewModel)
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(searchedChampions.size) {
                    ChampionCard(searchedChampions[it], navController)
                }
            }
        }
    }
}
