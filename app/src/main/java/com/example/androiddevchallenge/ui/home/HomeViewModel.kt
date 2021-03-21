package com.example.androiddevchallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.mockHourlyWeathers
import com.example.androiddevchallenge.data.mockParisCity
import com.example.androiddevchallenge.data.mockWeathers

class HomeViewModel : ViewModel() {
    private val _todayChangeExpanded = MutableLiveData(false)

    val todayChangeExpanded: LiveData<Boolean> get() = _todayChangeExpanded

    val currentCity = mockParisCity

    val currentWeather = mockWeathers.random()

    private val hourlyWeather = mockHourlyWeathers

    val hourlyGroupedWeather = hourlyWeather.chunked(size = 4)

    fun onTodayChangesExpansionStateChanged() {
        _todayChangeExpanded.value = !(_todayChangeExpanded.value ?: false)
    }

    fun isBottomSheetExpanded(): Boolean {
        return _todayChangeExpanded.value ?: false
    }
}