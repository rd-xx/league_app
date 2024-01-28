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
fun SpellCooldown(spell: ChampionSpellBean) {
    Column {
        Text(
            stringResource(R.string.spell_cooldown),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        if (spell.hasCooldown()) {
            Column {
                spell.cooldown.forEachIndexed { index, c ->
                    Text(stringResource(R.string.spell_level, index + 1, c))
                }
            }
        } else {
            Text(stringResource(R.string.spell_no_cooldown, spell.maxammo))
        }
    }
}
