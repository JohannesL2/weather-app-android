package com.example.weather_app.data

import androidx.lifecycle.*
import com.example.weather_app.data.model.WeatherModel
import com.example.weather_app.data.remote.NetworkResponse
import com.example.weather_app.data.remote.RetrofitInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class WeatherViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        _weatherResult.postValue(NetworkResponse.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = weatherApi.getCurrentWeather(city = city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.postValue(NetworkResponse.Success(it))
                        } ?: run {
                        _weatherResult.postValue(NetworkResponse.Error("Error: Response body is null"))
                        }
                    } else {
                        _weatherResult.postValue(NetworkResponse.Error("Error: ${response.message()}"))
                    }
                } catch (e: Exception) {
                    _weatherResult.postValue(NetworkResponse.Error("Error: ${e.message}"))
                }
            }
        }
    }
