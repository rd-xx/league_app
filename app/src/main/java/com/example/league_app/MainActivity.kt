package com.example.league_app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.example.league_app.ui.components.champions.ChampionsDrawer
import com.example.league_app.ui.components.layout.BottomBar
import com.example.league_app.ui.screens.ChampionDetailsScreen
import com.example.league_app.ui.screens.ChampionSpellDetailsScreen
import com.example.league_app.ui.screens.ChampionsScreen
import com.example.league_app.ui.screens.SettingsScreen
import com.example.league_app.ui.theme.LeagueTheme
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeagueTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }

        supportActionBar?.hide()
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
            Scaffold(bottomBar = { BottomBar(navController) { toggleDrawer() } }) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.ChampionsScreen.route
                    ) {
                        composable(Routes.ChampionsScreen.route) {
                            ChampionsScreen(navController, viewModel)
                        }

                        composable(
                            route = Routes.ChampionDetailsScreen.route,
                            arguments = listOf(navArgument("id") { type = NavType.StringType })
                        ) {
                            val id = it.arguments?.getString("id", "") ?: ""
                            ChampionDetailsScreen(navController, viewModel, id)
                        }

                        composable(route = Routes.ChampionSpellDetailsScreen.route,
                            arguments = listOf(
                                navArgument("championId") { type = NavType.StringType },
                                navArgument("id") { type = NavType.StringType }
                            )
                        ) {
                            val championId = it.arguments?.getString("championId", "") ?: ""
                            val id = it.arguments?.getString("id", "") ?: ""
                            ChampionSpellDetailsScreen(navController, viewModel, championId, id)
                        }

                        composable(route = Routes.SettingsScreen.route) {
                            SettingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}