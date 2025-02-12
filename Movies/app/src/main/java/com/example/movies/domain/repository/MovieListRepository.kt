package com.example.movies.domain.repository

import com.example.movies.domain.model.Movie
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>
    suspend fun getMovie(id:Int):Flow<Resource<List<Movie>>>
}