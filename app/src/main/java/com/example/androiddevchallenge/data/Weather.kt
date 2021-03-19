package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.androiddevchallenge.R

enum class WeatherStatus {
    Sunny,
    Cloudy,
    PartialCloudy,
    Raining,
    Snow,
    ThunderStorm,
    Tornado
}

data class Weather(
    val status: WeatherStatus,
    val temperature: Int,
    @StringRes val statusRes: Int,
    @DrawableRes val iconRes: Int,
    @DrawableRes val illustrationRes: Int,
    val city: String,
)

val mockWeather = Weather(
    status = WeatherStatus.Raining,
    temperature = 19,
    statusRes = R.string.raining,
    iconRes = R.drawable.ic_raining,
    illustrationRes = R.drawable.illus_raining,
    city = "Paris"
)