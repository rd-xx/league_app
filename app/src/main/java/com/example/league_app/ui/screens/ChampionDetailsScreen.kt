package com.example.league_app.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.league_app.R
import com.example.league_app.model.MainViewModel
import com.example.league_app.ui.components.champions.ChampionSpells
import com.example.league_app.ui.components.layout.Layout
import com.example.league_app.ui.components.layout.LoadingIndicator
import com.example.league_app.ui.components.layout.ScreenColumn

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
            ScreenColumn {
                GlideImage(
                    model = champion.getImageUrl(),
                    contentDescription = "${champion.name} image",
                    loading = placeholder(R.mipmap.ic_launcher_round),
                    failure = placeholder(R.mipmap.ic_launcher),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .width(200.dp)
                )
                Text(text = champion.lore)
                ChampionSpells(navController, champion)
            }
        }
    }
}
