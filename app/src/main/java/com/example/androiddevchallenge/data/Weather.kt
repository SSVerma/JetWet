package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.androiddevchallenge.R

enum class WeatherStatus {
    Sunny,
    Cloudy,
    PartialCloudy,
    Rainy,
    Snowy,
    ThunderStorm,
    Tornado
}

data class Weather(
    val status: WeatherStatus,
    val temperature: Int,
    @StringRes val statusRes: Int,
    @DrawableRes val iconRes: Int,
    @DrawableRes val illustrationRes: Int,
    val wind: String,
    val sunriseAt: String,
    val sunsetAt: String,
    val city: String,
)

data class HourlyWeather(
    val hour: String,
    val isCurrentHour: Boolean,
    val weather: Weather
)

val mockRainingWeather = Weather(
    status = WeatherStatus.Rainy,
    temperature = 19,
    statusRes = R.string.rainy,
    iconRes = R.drawable.ic_rainy,
    illustrationRes = R.drawable.illus_raining,
    wind = "Wind EN 8 km/h",
    sunriseAt = "06 : 00",
    sunsetAt = "18 : 35",
    city = "Paris"
)

val mockSnowWeather = Weather(
    status = WeatherStatus.Snowy,
    temperature = 8,
    statusRes = R.string.snowy,
    iconRes = R.drawable.ic_snowy,
    illustrationRes = R.drawable.illus_snowfalling,
    wind = "Wind EN 16 km/h",
    sunriseAt = "06 : 00",
    sunsetAt = "18 : 35",
    city = "Paris"
)

val mockSunnyWeather = Weather(
    status = WeatherStatus.Sunny,
    temperature = 32,
    statusRes = R.string.sunny,
    iconRes = R.drawable.ic_sunny,
    illustrationRes = R.drawable.illus_sunny_day,
    wind = "Wind EN 19 km/h",
    sunriseAt = "06 : 00",
    sunsetAt = "18 : 35",
    city = "Paris"
)

val mockWeathers = listOf(
    Weather(
        status = WeatherStatus.Rainy,
        temperature = 18,
        statusRes = R.string.rainy,
        iconRes = R.drawable.ic_rainy,
        illustrationRes = R.drawable.illus_raining,
        wind = "Wind EN 8 km/h",
        sunriseAt = "06 : 00",
        sunsetAt = "18 : 35",
        city = "Paris"
    ),
    Weather(
        status = WeatherStatus.Rainy,
        temperature = 19,
        statusRes = R.string.rainy,
        iconRes = R.drawable.ic_rainy,
        illustrationRes = R.drawable.illus_raining,
        wind = "Wind EN 8 km/h",
        sunriseAt = "06 : 00",
        sunsetAt = "18 : 35",
        city = "Paris"
    ),
    Weather(
        status = WeatherStatus.Rainy,
        temperature = 19,
        statusRes = R.string.rainy,
        iconRes = R.drawable.ic_rainy,
        illustrationRes = R.drawable.illus_raining,
        wind = "Wind EN 5 km/h",
        sunriseAt = "06 : 00",
        sunsetAt = "18 : 35",
        city = "Paris"
    ),
    Weather(
        status = WeatherStatus.Snowy,
        temperature = 10,
        statusRes = R.string.snowy,
        iconRes = R.drawable.ic_snowy,
        illustrationRes = R.drawable.illus_snowfalling,
        wind = "Wind EN 16 km/h",
        sunriseAt = "06 : 00",
        sunsetAt = "18 : 35",
        city = "Paris"
    ),
    Weather(
        status = WeatherStatus.Snowy,
        temperature = 10,
        statusRes = R.string.snowy,
        iconRes = R.drawable.ic_snowy,
        illustrationRes = R.drawable.illus_snowfalling,
        wind = "Wind EN 16 km/h",
        sunriseAt = "06 : 00",
        sunsetAt = "18 : 35",
        city = "Paris"
    ),
    Weather(
        status = WeatherStatus.Sunny,
        temperature = 32,
        statusRes = R.string.sunny,
        iconRes = R.drawable.ic_sunny,
        illustrationRes = R.drawable.illus_sunny_day,
        wind = "Wind EN 19 km/h",
        sunriseAt = "06 : 00",
        sunsetAt = "18 : 35",
        city = "Paris"
    ),
    Weather(
        status = WeatherStatus.Sunny,
        temperature = 32,
        statusRes = R.string.sunny,
        iconRes = R.drawable.ic_sunny,
        illustrationRes = R.drawable.illus_sunny_day,
        wind = "Wind EN 19 km/h",
        sunriseAt = "06 : 00",
        sunsetAt = "18 : 35",
        city = "Paris"
    ),
    Weather(
        status = WeatherStatus.Sunny,
        temperature = 32,
        statusRes = R.string.sunny,
        iconRes = R.drawable.ic_sunny,
        illustrationRes = R.drawable.illus_sunny_day,
        wind = "Wind EN 19 km/h",
        sunriseAt = "06 : 00",
        sunsetAt = "18 : 35",
        city = "Paris"
    )
)

val mockHourlyWeathers = mockWeathers.mapIndexed { index, weather ->
    val startHour = index + 6
    val formattedHour = if (startHour < 10) {
        "0${startHour} : 00"
    } else {
        "$startHour : 00"
    }
    HourlyWeather(
        hour = formattedHour,
        weather = weather,
        isCurrentHour = index == mockWeathers.size - 2
    )
}