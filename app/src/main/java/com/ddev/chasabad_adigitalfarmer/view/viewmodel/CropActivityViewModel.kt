package com.ddev.chasabad_adigitalfarmer.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class CropActivityViewModel(private val repository: Repository): ViewModel() {
    val cropResponse:MutableLiveData<List<CropData>> = MutableLiveData()

    fun getCropData(){
        viewModelScope.launch {
            val r = repository.getCropData()
            cropResponse.value = r
        }
    }
}