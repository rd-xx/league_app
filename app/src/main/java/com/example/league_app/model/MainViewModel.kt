package com.example.league_app.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var championsCollection = mutableListOf<SimpleChampionBean>()
    var champions = mutableListOf<ChampionBean>()

    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(Loaders())
    var searchText by mutableStateOf("")

    fun loadAll() {
        championsCollection.clear()
        errorMessage = ""
        isLoading.collection = true

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val result = ChampionsAPI.getAll()
                championsCollection.addAll(result)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage = "Erreur : ${e.message}"
            }

            isLoading.collection = false
        }
    }

    fun loadById(id: String) {
        errorMessage = ""
        isLoading.single = true

        if (champions.find { it.id == id } != null) {
            isLoading.single = false
            return
        }

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val result = ChampionsAPI.getById(id)
                champions.add(result)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage = "Erreur : ${e.message}"
            }

            isLoading.single = false
        }

    }

    fun search(input: String) {
        searchText = input
    }
}

class Loaders {
    var collection by mutableStateOf(false)
    var single by mutableStateOf(false)
}
