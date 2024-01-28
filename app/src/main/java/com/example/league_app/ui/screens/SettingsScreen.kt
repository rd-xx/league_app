package com.example.league_app.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.league_app.R
import com.example.league_app.ui.components.layout.Layout
import com.example.league_app.ui.components.layout.ScreenColumn
import com.example.league_app.ui.components.settings.LanguageButton

@Composable
fun SettingsScreen(navController: NavController) {
    Layout(navController, stringResource(R.string.settings)) {
        ScreenColumn {
            Card {
                ListItem(
                    colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    headlineContent = { Text(stringResource(R.string.language)) },
                    leadingContent = {
                        Icon(
                            Icons.Rounded.Place,
                            stringResource(R.string.language)
                        )
                    },
                    trailingContent = { LanguageButton() },
                )
            }
        }
    }
}
