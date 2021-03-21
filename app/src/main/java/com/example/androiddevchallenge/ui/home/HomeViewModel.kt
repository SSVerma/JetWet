/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
