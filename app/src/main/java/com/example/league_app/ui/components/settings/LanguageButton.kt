package com.example.league_app.ui.components.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.example.league_app.R
import com.example.league_app.ui.components.layout.CenteredRow

@Composable
fun LanguageButton() {
    val availableLocales = listOf(
        Pair("en", "English"),
        Pair("fr", "Français"),
    )
    var openDialog by remember { mutableStateOf(false) }
    val (selectedLocale, onOptionSelected) = remember {
        mutableStateOf(
            AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "en"
        )
    }

    when {
        openDialog -> {
            AlertDialog(onDismissRequest = { openDialog = false },
                title = { Text(stringResource(R.string.language)) },
                text = {
                    LazyColumn(Modifier.selectableGroup()) {
                        items(availableLocales.size) {
                            val locale = availableLocales[it]

                            CenteredRow(
                                Modifier
                                    .selectable(
                                        selected = selectedLocale == locale.first,
                                        onClick = { onOptionSelected(locale.first) },
                                        role = Role.RadioButton
                                    )
                                    .padding(8.dp),
                            ) {
                                RadioButton(
                                    selected = selectedLocale == locale.first,
                                    onClick = null
                                )
                                Text(locale.second)
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        val localeList = LocaleListCompat.forLanguageTags(selectedLocale)
                        AppCompatDelegate.setApplicationLocales(localeList)

                        openDialog = false
                    }) {
                        Text(stringResource(R.string.confirm))
                    }
                })
        }
    }

    IconButton(onClick = { openDialog = true }) {
        Icon(
            Icons.Rounded.Edit,
            contentDescription = stringResource(R.string.description_edit_language)
        )
    }
}
