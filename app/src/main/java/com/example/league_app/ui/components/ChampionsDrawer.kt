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
import androidx.navigation.NavController
import com.example.league_app.beans.Routes
import com.example.league_app.model.MainViewModel

@Composable
fun ChampionsDrawer(
    viewModel: MainViewModel,
    navController: NavController?,
    onClose: () -> Unit
) {
    LaunchedEffect("collection") {
        viewModel.loadAll()
    }

    if (viewModel.isLoading.collection) {
        Text(text = "Chargement...")
        return
    }

    if (viewModel.errorMessage.isNotEmpty()) {
        Text(text = viewModel.errorMessage)
        return
    }

    Text("Champions", modifier = Modifier.padding(16.dp))
    Divider()
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(viewModel.championsCollection.size) {
            val champion = viewModel.championsCollection[it]

            NavigationDrawerItem(
                label = { Text(text = champion.name) },
                selected = false,
                onClick = {
                    navController?.navigate(Routes.ChampionDetailsScreen.addParam(champion.id))
                    onClose()
                }
            )
        }
    }
}
