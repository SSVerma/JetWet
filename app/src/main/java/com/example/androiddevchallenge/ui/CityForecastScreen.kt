package com.example.androiddevchallenge.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.CityWeather
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.data.mockCityWeathers
import com.example.androiddevchallenge.data.mockSunnyWeather
import com.example.androiddevchallenge.ui.common.AppTopAppBar
import com.example.androiddevchallenge.ui.common.DegreeText
import com.example.androiddevchallenge.ui.common.GradientDivider
import com.example.androiddevchallenge.ui.common.IllustratedBackground

@Composable
fun CityForecastScreen(onBackPressed: () -> Unit) {
    IllustratedBackground {
        Column {
            AppTopAppBar(titleRes = R.string.city_forecast, onBackPressed = onBackPressed)
            SunriseSunsetSection(
                weather = mockSunnyWeather,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            )
            CityWeatherSection(
                cities = mockCityWeathers,
                modifier = Modifier.background(
                    color = MaterialTheme.colors.background,
                    shape = MaterialTheme.shapes.large
                ),
            )
        }
    }
}

@Composable
fun CityWeatherSection(
    cities: List<CityWeather>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier,
        content = {
            items(cities) { city ->
                CityWeatherItem(
                    cityWeather = city,
                    modifier = Modifier.padding(vertical = 40.dp, horizontal = 32.dp)
                )
                GradientDivider()
            }
        }
    )
}

@Composable
fun CityWeatherItem(
    cityWeather: CityWeather, modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Text(
            text = cityWeather.city,
            style = MaterialTheme.typography.body1.copy(fontSize = 24.sp),
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.54f),
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )
        Column(modifier = Modifier.padding(end = 32.dp)) {
            DegreeText(
                text = cityWeather.weather.temperature.toString(),
                style = MaterialTheme.typography.body1.copy(fontSize = 32.sp),
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.87f),
            )
            Text(
                text = cityWeather.currentTime,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.87f),
            )
        }
        Icon(
            painter = painterResource(id = cityWeather.weather.iconRes),
            contentDescription = stringResource(id = R.string.accessibility_weather_status_icon),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun SunriseSunsetSection(weather: Weather, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {
        SunriseItem(iconRes = R.drawable.ic_sunrise, text = weather.sunriseAt)
        SunriseItem(iconRes = R.drawable.ic_sunset, text = weather.sunsetAt)
    }
}

@Composable
fun SunriseItem(
    @DrawableRes iconRes: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .padding(end = 12.dp)
                .size(48.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1.copy(fontSize = 18.sp),
            color = MaterialTheme.colors.onPrimary
        )
    }
}