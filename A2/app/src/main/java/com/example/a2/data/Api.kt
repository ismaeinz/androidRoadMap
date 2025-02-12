package com.example.a2.data

import com.example.a2.data.model.Products
import retrofit2.http.GET

interface Api {
    @GET("products")
    suspend fun getProductsList(): Products


    companion object {
        const val BASE_URL = "https://dummyjson.com/"
        const val END_POINT = "products"
    }
}