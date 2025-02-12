package com.example.movies.data.repository

import coil3.network.HttpException
import com.example.movies.data.local.movie.MovieDataBase
import com.example.movies.data.mappers.toMovie
import com.example.movies.data.mappers.toMovieEntity
import com.example.movies.data.remote.MovieApi
import com.example.movies.domain.model.Movie
import com.example.movies.domain.repository.MovieListRepository
import com.example.movies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDataBase: MovieDataBase
)

    : MovieListRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading(true))
            val localMovieList =
                movieDataBase.movieDao.getMovieByCategory(category)
            val shouldLoadLocalMovie =
                localMovieList.isEmpty() && !forceFetchFromRemote
            if (shouldLoadLocalMovie) {
                emit(
                    Resource.Success(
                        data = localMovieList.map { movieEntity ->
                            movieEntity.toMovie(category)
                        }
                    )
                )
                emit(Resource.Loading(false))
                return@flow
            }
            val movieListFromApi = try {
                movieApi.getMoviesList(category, page)
            } catch (e: IOException) {
                emit(Resource.Error(message = "Error loading movies"))
                return@flow

            } catch (e: HttpException) {
                emit(Resource.Error(message = "Error loading movies"))
                return@flow


            } catch (e: Exception) {
                emit(Resource.Error(message = "Error loading movies"))
                return@flow

            }
            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }
            movieDataBase.movieDao.upsertMovies(movieEntities)
            emit(
                Resource.Success(
                    movieEntities.map {
                        it.toMovie(category)
                    }
                )
            )
            emit(Resource.Loading(false))


        }

    override suspend fun getMovie(id: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))
            val localMovieList =
                movieDataBase.movieDao.getMovieByCategory(category)
            val shouldLoadLocalMovie =
                localMovieList.isEmpty() && !forceFetchFromRemote
            if (shouldLoadLocalMovie) {
                emit(
                    Resource.Success(
                        data = localMovieList.map { movieEntity ->
                            movieEntity.toMovie(category)
                        }
                    )
                )
                emit(Resource.Loading(false))
                return@flow
            }
            val movieListFromApi = try {
                movieApi.getMoviesList(category, page)
            } catch (e: IOException) {
                emit(Resource.Error(message = "Error loading movies"))
                return@flow

            } catch (e: HttpException) {
                emit(Resource.Error(message = "Error loading movies"))
                return@flow


            } catch (e: Exception) {
                emit(Resource.Error(message = "Error loading movies"))
                return@flow

            }
            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }
            movieDataBase.movieDao.upsertMovies(movieEntities)
            emit(
                Resource.Success(
                    movieEntities.map {
                        it.toMovie(category)
                    }
                )
            )
            emit(Resource.Loading(false))


        }
    }

}