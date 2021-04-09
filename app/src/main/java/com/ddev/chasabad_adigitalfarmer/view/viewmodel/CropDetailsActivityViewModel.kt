package com.ddev.chasabad_adigitalfarmer.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddev.chasabad_adigitalfarmer.model.crop.CropData
import com.ddev.chasabad_adigitalfarmer.model.crop.CropFertilizer
import com.ddev.chasabad_adigitalfarmer.repository.Repository
import kotlinx.coroutines.launch

class CropDetailsActivityViewModel(private val repository: Repository):ViewModel() {

    val fResponse: MutableLiveData<List<CropData>> = MutableLiveData()

    fun getFertilizer(){
        viewModelScope.launch {
            val f = repository.getCropData()
            fResponse.value = f
        }
    }
}