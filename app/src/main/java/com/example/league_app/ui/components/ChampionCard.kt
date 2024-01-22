package com.example.league_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.league_app.R
import com.example.league_app.beans.Routes
import com.example.league_app.model.SimpleChampionBean

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampionCard(champion: SimpleChampionBean, navController: NavController?) {
    val onClick = {
        navController?.navigate(Routes.ChampionDetailsScreen.addParam(champion.id))
    }

    Card(modifier = Modifier.clickable { onClick() }) {
        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            GlideImage(
                model = champion.getImageUrl(),
                contentDescription = "${champion.name}'s icon",
                loading = placeholder(R.mipmap.ic_launcher_round),
                failure = placeholder(R.mipmap.ic_launcher),
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(champion.name, fontSize = 20.sp)
                Text(champion.blurb, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "Go to details",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}