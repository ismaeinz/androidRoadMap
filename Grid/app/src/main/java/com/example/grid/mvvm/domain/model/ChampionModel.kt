package com.example.grid.mvvm.domain.model

import coil3.Image


data class ChampionModel(
    val id: String? = "",
    val image: Image?,
    val key: String? = "",
    val lore: String? = "",
    val blurb: String? = "",
    val name: String? = "",
    val tags: List<String> = listOf(),
    val title: String? = ""
)