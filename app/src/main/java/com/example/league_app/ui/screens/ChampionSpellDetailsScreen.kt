package com.example.league_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.league_app.R
import com.example.league_app.model.MainViewModel
import com.example.league_app.ui.components.layout.CenteredRow
import com.example.league_app.ui.components.layout.Layout
import com.example.league_app.ui.components.layout.ScreenColumn
import com.example.league_app.ui.components.spells.SpellCooldown
import com.example.league_app.ui.components.spells.SpellCost

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampionSpellDetailsScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    championId: String,
    id: String,
) {
    val champion = viewModel.champions.find { it.id == championId }
    val spell = champion?.spells?.find { it.id == id }

    if (spell == null) {
        navController.popBackStack()
        return
    }

    Layout(navController, spell.name) {
        ScreenColumn {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CenteredRow {
                    GlideImage(
                        model = champion.getImageUrl(),
                        contentDescription = "${champion.name} image",
                        loading = placeholder(R.mipmap.ic_launcher_round),
                        failure = placeholder(R.mipmap.ic_launcher),
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                    )
                    Text(champion.name, fontSize = 20.sp)
                }
                Divider()
                CenteredRow {
                    Spacer(modifier = Modifier.weight(1f))
                    Text("${spell.getKey(champion.spells)} ${stringResource(R.string.spell)}")
                    GlideImage(
                        model = spell.getImageUrl(),
                        contentDescription = "${champion.name} image",
                        loading = placeholder(R.mipmap.ic_launcher_round),
                        failure = placeholder(R.mipmap.ic_launcher),
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp)
                    )
                }
            }

            Text(spell.description)
            SpellCooldown(spell)
            SpellCost(spell)
        }
    }
}
