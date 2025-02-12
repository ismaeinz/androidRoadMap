package com.example.movies.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovies(movieList: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity WHERE id =:id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * FROM MovieEntity WHERE category =:category")
    suspend fun getMovieByCategory(category: String): List<MovieEntity>
}