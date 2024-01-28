package com.example.league_app.ui.components.champions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.league_app.model.ChampionBean
import com.example.league_app.ui.components.spells.SpellCard

@Composable
fun ChampionSpells(navController: NavController, champion: ChampionBean) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Spells", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            champion.spells.forEach {
                SpellCard(
                    navController = navController,
                    champion = champion,
                    spell = it
                )
            }
        }
    }
}
