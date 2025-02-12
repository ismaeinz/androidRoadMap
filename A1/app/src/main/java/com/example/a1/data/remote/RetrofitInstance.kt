package com.example.a1.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val baseUrl = "https://api.weatherapi.com/";


    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val weatherApi: WeatherApi = getInstance().create(WeatherApi::class.java)

}
//https://api.weatherapi.com/
// v1/current.json
// ?
// key=2c737010a5ef434eb0883741250201
// &
// q=London
// &
// aqi=yes