package com.example.league_app.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var champions = mutableListOf<ChampionBean>()
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun load() {
        champions.clear()

        errorMessage.value = ""
        isLoading.value = true

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val result = ChampionsAPI.loadChampions()
                champions.addAll(result)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = "Erreur : ${e.message}"
            }

            isLoading.value = false
        }
    }
}