package com.example.a1.data.remote

import com.example.a1.data.remote.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {


    @GET("v1/current.json")
    suspend fun getWeather(
        @Query("key") apikey: String, @Query("q") city: String
    ): Response<WeatherModel>

}
//https://api.weatherapi.com/
// v1/current.json
// ?
// key=2c737010a5ef434eb0883741250201
// &
// q=London
// &
// aqi=yes