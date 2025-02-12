package com.example.a1.presention.weather_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a1.data.remote.RetrofitInstance
import com.example.a1.data.remote.model.WeatherModel
import kotlinx.coroutines.launch

class WeatherPageViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response =
                    weatherApi.getWeather("2c737010a5ef434eb0883741250201", city)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("Error")

                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Exception")

            }
        }
    }
}