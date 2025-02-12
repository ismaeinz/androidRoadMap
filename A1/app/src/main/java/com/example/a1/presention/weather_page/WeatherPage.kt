package com.example.a1.presention.weather_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WeatherPage(viewModel: WeatherPageViewModel) {
    var valueSearch: String by rememberSaveable { mutableStateOf("") }
    val weatherResult = viewModel.weatherResult.observeAsState()
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = valueSearch,
                onValueChange = { valueSearch = it },
                label = { Text(text = "search for any location") })
            IconButton(onClick = {
                viewModel.getData(valueSearch)
            }) {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = ""
                )
            }

        }


        when (val result = weatherResult.value) {
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }

            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }

            is NetworkResponse.Success -> {
                Text(text = result.data.toString())
            }

            null -> {
            }
        }
    }
}

