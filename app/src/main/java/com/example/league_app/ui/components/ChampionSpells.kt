package com.example.league_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize.Max
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.league_app.R
import com.example.league_app.model.ChampionBean

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampionSpells(champion: ChampionBean) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text("Spells", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            champion.spells.forEach {
                Card {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GlideImage(
                            model = it.getImageUrl(), contentDescription = "Spell",
                            loading = placeholder(R.mipmap.ic_launcher_round),
                            failure = placeholder(R.mipmap.ic_launcher),
                            modifier = Modifier
                                .height(48.dp)
                                .width(48.dp)
                        )

                        Text(
                            it.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(Max)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(it.getKey(), fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
                    }
                }
            }
        }
    }
}
