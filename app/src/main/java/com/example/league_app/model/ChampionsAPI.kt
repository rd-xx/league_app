package com.example.league_app.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object ChampionsAPI {
    val client = OkHttpClient()
    val gson = Gson()

    const val URL_API = "https://ddragon.leagueoflegends.com/cdn/14.1.1/data/en_US/champion.json"

    fun loadChampions(): List<ChampionBean> {
        val json = sendGet(URL_API)
        println(json)
        return gson.fromJson(json, ChampionsBean::class.java).data.values.toList()
    }

    fun sendGet(url: String): String {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        return client.newCall(request).execute().use { //it:Response
            if (!it.isSuccessful || it.body == null) {
                throw Exception("Erreur serveur :${it.code}")
            }

            it.body!!.string()
        }
    }
}

/* -------------------------------- */
// Bean
/* -------------------------------- */
data class ChampionsBean(
    val data: Map<String, ChampionBean>,
)

data class ChampionBean(
    val name: String,
    val title: String,
    val blurb: String,
    val image: ChampionImageBean,
    val tags: List<String>,
)

data class ChampionImageBean(
    val full: String,
    val sprite: String,
    val w: Int,
    val h: Int,
)