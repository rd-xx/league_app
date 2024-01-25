package com.example.league_app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.league_app.R
import com.example.league_app.beans.Routes

@Composable
fun BottomBar(navController: NavController, toggleDrawer: () -> Unit) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { toggleDrawer() }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.description_open_champions_drawer),
                )
            }
            IconButton(onClick = { navController.navigate(Routes.ChampionsScreen.route) }) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = stringResource(R.string.description_search_champions),
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = stringResource(R.string.description_go_to_settings),
                )
            }
        },
    )
}