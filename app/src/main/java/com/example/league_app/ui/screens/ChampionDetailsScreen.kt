package com.example.league_app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.league_app.R
import com.example.league_app.model.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun ChampionDetailsScreen(
    navController: NavHostController?,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    id: String
) {
    LaunchedEffect(id) {
        viewModel.loadById(id)
    }

    val champion = viewModel.champions.find { it.id == id }

    if (viewModel.isLoading.single || champion == null) {
        Text("Chargement...")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(champion.name) }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Red),
                verticalArrangement = Arrangement.spacedBy(16.dp),
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
            }
        }
    }
}
