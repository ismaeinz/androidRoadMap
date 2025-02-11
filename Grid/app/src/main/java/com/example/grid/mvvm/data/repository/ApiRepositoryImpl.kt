package com.example.grid.mvvm.data.repository

import com.example.grid.mvvm.domain.model.ChampionResponseModel
import io.ktor.client.HttpClient

class ApiRepositoryImpl(
    private val httpClient: HttpClient
) : ApiRepository {
    companion object {
        const val BASEURL =
            "https://ddragon.leagueoflegends.com/cdn/15.3.1/data/en_US/"
    }

    override suspend fun getAllChampions(): List<ChampionResponseModel> =
        httpClient.getApiResponse("champion.json")


    override suspend fun getChampionByName(name: String): ChampionResponseModel =
        httpClient.getApiResponse("$name.json")

}