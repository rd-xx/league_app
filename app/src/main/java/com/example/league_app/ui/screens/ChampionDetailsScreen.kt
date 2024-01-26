package com.example.league_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.league_app.R
import com.example.league_app.model.MainViewModel
import com.example.league_app.ui.components.ChampionSpells
import com.example.league_app.ui.components.Layout
import com.example.league_app.ui.components.LoadingIndicator

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampionDetailsScreen(navController: NavHostController, viewModel: MainViewModel, id: String) {
    LaunchedEffect(id) { viewModel.loadById(id) }

    val champion = viewModel.champions.find { it.id == id }

    Layout(navController, id) {
        if (viewModel.isLoading.single) {
            LoadingIndicator()
        }

        if (!viewModel.isLoading.single && champion != null) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                GlideImage(
                    model = champion.getImageUrl(),
                    contentDescription = "${champion.name} image",
                    loading = placeholder(R.mipmap.ic_launcher_round),
                    failure = placeholder(R.mipmap.ic_launcher),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .width(200.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(text = champion.lore)
                ChampionSpells(champion)
            }
        }
    }
}
