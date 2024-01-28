package com.example.league_app.ui.components.spells

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.league_app.R
import com.example.league_app.model.ChampionSpellBean

@Composable
fun SpellCost(spell: ChampionSpellBean) {
    Column {
        Text("Costs", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        if (spell.isManaless()) {
            Text(stringResource(R.string.spell_manaless))
        } else {
            Column {
                spell.cost.forEachIndexed { index, c ->
                    Text(stringResource(R.string.spell_level, index + 1, c))
                }
            }
        }
    }
}
