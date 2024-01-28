package com.example.league_app.ui.components.spells

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.league_app.R
import com.example.league_app.beans.Routes
import com.example.league_app.model.ChampionBean
import com.example.league_app.model.ChampionSpellBean
import com.example.league_app.ui.components.layout.CenteredRow

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SpellCard(navController: NavController, champion: ChampionBean, spell: ChampionSpellBean) {
    val onClick = {
        navController.navigate(
            Routes.ChampionSpellDetailsScreen.addParam(
                championId = champion.id,
                id = spell.id
            )
        )
    }

    Card(modifier = Modifier.clickable { onClick() }) {
        CenteredRow {
            GlideImage(
                model = spell.getImageUrl(), contentDescription = "Spell",
                loading = placeholder(R.mipmap.ic_launcher_round),
                failure = placeholder(R.mipmap.ic_launcher),
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
            )

            Text(
                spell.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(IntrinsicSize.Max)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(spell.getKey(), fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
        }
    }
}
