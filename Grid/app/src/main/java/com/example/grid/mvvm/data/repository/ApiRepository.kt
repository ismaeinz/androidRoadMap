package com.example.grid.mvvm.data.repository

import com.example.grid.mvvm.domain.model.ChampionResponseModel

interface ApiRepository {
    suspend fun getAllChampions(): ApiResponse<ChampionResponseModel>
    suspend fun getChampionByName(name: String): ApiResponse<ChampionResponseModel>
}