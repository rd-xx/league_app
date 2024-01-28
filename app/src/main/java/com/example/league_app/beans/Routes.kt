package com.example.league_app.beans

sealed class Routes(val route: String) {
    object ChampionsScreen : Routes("championsScreen")
    object ChampionDetailsScreen : Routes("championDetailsScreen/{id}") {
        fun addParam(id: String) = "championDetailsScreen/$id"
    }

    object ChampionSpellDetailsScreen : Routes("championSpellDetailsScreen/{championId}/{id}") {
        fun addParam(championId: String, id: String) = "championSpellDetailsScreen/$championId/$id"
    }

    object SettingsScreen : Routes("settingsScreen")
}