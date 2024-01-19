package com.example.league_app.beans

sealed class Routes(val route: String) {
    object ChampionsScreen : Routes("championsScreen")
    object ChampionDetailsScreen : Routes("championDetailsScreen/{data}") {
        fun addParam(position: Int) = "championDetailsScreen/$position"
    }

    object SettingsScreen : Routes("settingsScreen")
}