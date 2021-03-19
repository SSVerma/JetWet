package com.example.androiddevchallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _todayChangeExpanded = MutableLiveData(false)

    val todayChangeExpanded: LiveData<Boolean> get() = _todayChangeExpanded

    fun onTodayChangesExpansionStateChanged() {
        _todayChangeExpanded.value = !(_todayChangeExpanded.value ?: false)
    }
}