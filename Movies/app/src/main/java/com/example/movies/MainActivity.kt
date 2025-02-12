package com.example.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {}
    }
}
//https://github.com/ahmed-guedmioui/Movies-App-Tutorial/blob/part-1/app/src/main/java/com/ahmedapps/moviesapp/movieList/data/mappers/MovieMapper.kt