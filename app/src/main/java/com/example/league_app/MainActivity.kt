package com.example.league_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.league_app.beans.Routes
import com.example.league_app.model.MainViewModel
import com.example.league_app.ui.components.ChampionsDrawer
import com.example.league_app.ui.screens.ChampionDetailsScreen
import com.example.league_app.ui.screens.ChampionsScreen
import com.example.league_app.ui.screens.SettingsScreen
import com.example.league_app.ui.theme.LeagueTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeagueTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val toggleDrawer = {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    val viewModel: MainViewModel = viewModel()
    val navController: NavHostController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                ChampionsDrawer(viewModel, navController, onClose = { toggleDrawer() })
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = { toggleDrawer() }) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "Open champions drawer",
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Search champions",
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Go to settings",
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController,
                    startDestination = Routes.ChampionsScreen.route
                ) {
                    composable(Routes.ChampionsScreen.route) {
                        ChampionsScreen()
                    }

                    composable(
                        route = Routes.ChampionDetailsScreen.route,
                        arguments = listOf(navArgument("data") { type = NavType.StringType })
                    ) {
                        val id = it.arguments?.getString("data", "") ?: ""
                        ChampionDetailsScreen(navController, viewModel, id)
                    }

                    composable(route = Routes.SettingsScreen.route) {
                        SettingsScreen()
                    }
                }
            }
        }
    }
}
