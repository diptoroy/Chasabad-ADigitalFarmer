package com.ddev.chasabad_adigitalfarmer.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddev.chasabad_adigitalfarmer.model.weatherDailyModel.WeatherUviData
import com.ddev.chasabad_adigitalfarmer.model.weatherModel.WeatherData
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(private val repository: Repository): ViewModel() {
    val dataResponse: MutableLiveData<Response<WeatherData>> = MutableLiveData()
    val dailyDataResponse: MutableLiveData<Response<WeatherUviData>> = MutableLiveData()

    fun getData(lat:String,lon:String,appid:String){
        viewModelScope.launch {
            val r = repository.getData(lat,lon,appid)
            dataResponse.value = r
        }
    }

    fun getUviData(lat:String,lon:String,appid:String){
        viewModelScope.launch {
            val r = repository.getUviData(lat,lon,appid)
            dailyDataResponse.value = r
        }
    }
}