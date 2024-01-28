package com.example.league_app.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

object ChampionsAPI {
    val client = OkHttpClient()
    val gson = Gson()

    const val API_BASE_URL = "https://ddragon.leagueoflegends.com/cdn/14.1.1/data/en_US"

    fun getAll(): List<SimpleChampionBean> {
        val json = sendGet("${API_BASE_URL}/champion.json")
        return gson.fromJson(json, ChampionsCollectionBean::class.java).data.values.toList()
    }

    fun getById(id: String): ChampionBean {
        val json = sendGet("${API_BASE_URL}/champion/$id.json")
        return gson.fromJson(json, ChampionsBean::class.java).data.values.toList()[0]
    }

    private fun sendGet(url: String): String {
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
data class ChampionsCollectionBean(
    val data: Map<String, SimpleChampionBean>,
)

data class ChampionsBean(
    val data: Map<String, ChampionBean>,
)

data class SimpleChampionBean(
    val id: String,
    val name: String,
    val image: ImageBean,
    val blurb: String,
) {
    fun getImageUrl(): String {
        return "https://ddragon.leagueoflegends.com/cdn/14.1.1/img/champion/${image.full}"
    }
}

data class ChampionBean(
    val id: String,
    val name: String,
    val title: String,
    val lore: String,
    val image: ImageBean,
    val tags: List<String>,
    val stats: ChampionStatsBean,
    val spells: List<ChampionSpellBean>,
    val passive: ChampionPassiveBean,
) {
    fun getImageUrl(): String {
        return "https://ddragon.leagueoflegends.com/cdn/14.2.1/img/champion/${image.full}"
    }
}


data class ChampionStatsBean(
    val hp: Double,
    val hpperlevel: Double,

    val mp: Double,
    val mpperlevel: Double,

    val armor: Double,
    val armorperlevel: Double,

    val spellblock: Double,
    val spellblockperlevel: Double,


    val hpregen: Double,
    val hpregenperlevel: Double,

    val mpregen: Double,
    val mpregenperlevel: Double,

    val crit: Double,
    val critperlevel: Double,

    val attackdamage: Double,
    val attackdamageperlevel: Double,
    val attackspeedperlevel: Double,
    val attackspeed: Double,
    val attackrange: Double,

    val movespeed: Double,
)

data class ChampionSpellBean(
    val id: String,
    val name: String,
    val description: String,
    val image: ImageBean,
    val cooldown: List<Double>,
    val cost: List<Double>,
    val costBurn: String,
    val maxammo: String,
    val range: List<Double>,
) {
    fun getImageUrl(): String {
        return "https://ddragon.leagueoflegends.com/cdn/14.2.1/img/spell/${image.full}"
    }

    fun getKey(spells: List<ChampionSpellBean>): String {
        val keys = listOf("Q", "W", "E", "R")

        return keys[spells.indexOf(this)]
    }

    fun hasCooldown(): Boolean {
        return maxammo == "-1"
    }

    fun isManaless(): Boolean {
        return costBurn == "0"
    }
}

data class ChampionPassiveBean(
    val name: String,
    val description: String,
    val image: ImageBean,
)

data class ImageBean(
    val full: String,
    val sprite: String,
    val w: Int,
    val h: Int,
)