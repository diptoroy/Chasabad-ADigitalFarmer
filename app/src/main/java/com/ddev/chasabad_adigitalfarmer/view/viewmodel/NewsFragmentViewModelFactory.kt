package com.ddev.chasabad_adigitalfarmer.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ddev.chasabad_adigitalfarmer.repository.Repository

class NewsFragmentViewModelFactory(val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsFragmentViewModel(repository) as T
    }
}