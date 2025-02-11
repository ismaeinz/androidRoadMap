package com.example.grid.mvvm.data.di

import android.net.http.HttpResponseCache.install
import com.example.grid.mvvm.data.repository.ApiRepository
import com.example.grid.mvvm.data.repository.ApiRepositoryImpl
import com.google.common.net.HttpHeaders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(OkHttpClient.create()) {
        defaultRequest {
            uri(ApiRepositoryImpl.BASEURL)
            header(HttpHeaders.ContentType, "application/json")
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    @Provides
    @Singleton
    fun provideApiRepository(httpClient: HttpClient): ApiRepository =
        ApiRepositoryImpl(httpClient = httpClient)
}