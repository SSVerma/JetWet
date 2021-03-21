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
package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.androiddevchallenge.R

/*
    Sunny,
    Cloudy,
    PartialCloudy,
    Rainy,
    Snowy,
    ThunderStorm
 */

sealed class Weather(
    @StringRes val statusRes: Int,
    @DrawableRes val iconRes: Int,
    @DrawableRes val illustrationRes: Int,
    open val temperature: Int,
    open val wind: String,
    open val sunriseAt: String,
    open val sunsetAt: String,
) {
    data class Rainy(
        override val temperature: Int,
        override val wind: String,
        override val sunriseAt: String,
        override val sunsetAt: String,
    ) : Weather(
        statusRes = R.string.rainy,
        iconRes = R.drawable.ic_rainy,
        illustrationRes = R.drawable.illus_raining,
        temperature = temperature,
        wind = wind,
        sunriseAt = sunriseAt,
        sunsetAt = sunsetAt,
    )

    data class Snowy(
        override val temperature: Int,
        override val wind: String,
        override val sunriseAt: String,
        override val sunsetAt: String,
    ) : Weather(
        statusRes = R.string.snowy,
        iconRes = R.drawable.ic_snowy,
        illustrationRes = R.drawable.illus_snowfalling,
        temperature = temperature,
        wind = wind,
        sunriseAt = sunriseAt,
        sunsetAt = sunsetAt,
    )

    data class Sunny(
        override val temperature: Int,
        override val wind: String,
        override val sunriseAt: String,
        override val sunsetAt: String,
    ) : Weather(
        statusRes = R.string.sunny,
        iconRes = R.drawable.ic_sunny,
        illustrationRes = R.drawable.illus_sunny_day,
        temperature = temperature,
        wind = wind,
        sunriseAt = sunriseAt,
        sunsetAt = sunsetAt,
    )

    data class Cloudy(
        override val temperature: Int,
        override val wind: String,
        override val sunriseAt: String,
        override val sunsetAt: String,
    ) : Weather(
        statusRes = R.string.cloudy,
        iconRes = R.drawable.ic_cloudy,
        illustrationRes = R.drawable.illus_cloudy,
        temperature = temperature,
        wind = wind,
        sunriseAt = sunriseAt,
        sunsetAt = sunsetAt,
    )

    data class Storm(
        override val temperature: Int,
        override val wind: String,
        override val sunriseAt: String,
        override val sunsetAt: String,
    ) : Weather(
        statusRes = R.string.storm,
        iconRes = R.drawable.ic_storm,
        illustrationRes = R.drawable.illus_storm,
        temperature = temperature,
        wind = wind,
        sunriseAt = sunriseAt,
        sunsetAt = sunsetAt,
    )
}

data class HourlyWeather(
    val hour: String,
    val isCurrentHour: Boolean,
    val weather: Weather
)

data class City(
    val name: String
)

data class CityWeather(
    val city: String,
    val currentTime: String,
    val weather: Weather
)

data class DayWeather(
    val day: String,
    val weather: Weather
)

val mockParisCity = City(name = "Paris")

val mockRainyWeather = Weather.Rainy(
    temperature = 19,
    wind = "Wind EN 8 km/h",
    sunriseAt = "06 : 00",
    sunsetAt = "18 : 35",
)

val mockSnowWeather = Weather.Snowy(
    temperature = 10,
    wind = "Wind EN 16 km/h",
    sunriseAt = "06 : 15",
    sunsetAt = "18 : 35",
)

val mockSunnyWeather = Weather.Sunny(
    temperature = 32,
    wind = "Wind EN 19 km/h",
    sunriseAt = "06 : 08",
    sunsetAt = "18 : 35",
)

val mockCloudyWeather = Weather.Cloudy(
    temperature = 16,
    wind = "Wind EN 19 km/h",
    sunriseAt = "06 : 08",
    sunsetAt = "18 : 35",
)

val mockStormWeather = Weather.Storm(
    temperature = 11,
    wind = "Wind EN 19 km/h",
    sunriseAt = "06 : 08",
    sunsetAt = "18 : 35",
)

val mockWeathers = listOf(
    mockRainyWeather,
    mockSnowWeather,
    mockSunnyWeather,
    mockCloudyWeather,
    mockStormWeather
)

private val mockTodayWeathers = listOf(
    mockCloudyWeather,
    mockCloudyWeather,
    mockRainyWeather,
    mockRainyWeather,
    mockRainyWeather,
    mockSunnyWeather.copy(temperature = 22),
    mockSunnyWeather.copy(temperature = 24),
    mockSunnyWeather.copy(temperature = 27),
)

val mockHourlyWeathers = mockTodayWeathers.mapIndexed { index, weather ->
    val startHour = index + 6
    val formattedHour = if (startHour < 10) {
        "0$startHour : 00"
    } else {
        "$startHour : 00"
    }
    HourlyWeather(
        hour = formattedHour,
        weather = weather,
        isCurrentHour = index == mockTodayWeathers.size - 2
    )
}

val mockCityWeathers = listOf(
    CityWeather(
        city = "Paris",
        currentTime = "11:25",
        weather = mockRainyWeather.copy(temperature = 22),
    ),
    CityWeather(
        city = "London",
        currentTime = "10:25",
        weather = mockSnowWeather.copy(temperature = 24),
    ),
    CityWeather(
        city = "Naples",
        currentTime = "11:35",
        weather = mockSunnyWeather.copy(temperature = 27),
    ),
    CityWeather(
        city = "Brussels",
        currentTime = "10:16",
        weather = mockSunnyWeather.copy(temperature = 21),
    )
)

val mockDayWeathers = listOf(
    DayWeather(
        day = "Monday, 22 March",
        weather = mockRainyWeather.copy(temperature = 18),
    ),
    DayWeather(
        day = "Tuesday, 23 March",
        weather = mockRainyWeather.copy(temperature = 18),
    ),
    DayWeather(
        day = "Wednesday, 24 March",
        weather = mockSnowWeather.copy(temperature = 12),
    ),
    DayWeather(
        day = "Thursday, 25 March",
        weather = mockSunnyWeather.copy(temperature = 22),
    ),
    DayWeather(
        day = "Friday, 26 March",
        weather = mockSunnyWeather.copy(temperature = 23),
    ),
    DayWeather(
        day = "Saturday, 27 March",
        weather = mockSunnyWeather.copy(temperature = 26),
    ),
    DayWeather(
        day = "Sunday, 28 March",
        weather = mockSunnyWeather.copy(temperature = 25),
    ),
)
