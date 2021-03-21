package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.androiddevchallenge.data.DayWeather
import com.example.androiddevchallenge.data.mockDayWeathers
import com.example.androiddevchallenge.ui.common.AppTopAppBar
import com.example.androiddevchallenge.ui.common.DegreeText
import com.example.androiddevchallenge.ui.common.GradientDivider
import com.example.androiddevchallenge.ui.common.IllustratedBackground

@Composable
fun FutureForecastScreen(
    cityName: String,
    onBackPressed: () -> Unit
) {
    IllustratedBackground {
        Column {
            AppTopAppBar(titleRes = R.string.future_forecast, onBackPressed = onBackPressed)
            Text(
                text = cityName,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 24.dp)
            )
            DayForecastSection(
                days = mockDayWeathers,
                modifier = Modifier.background(
                    color = MaterialTheme.colors.background,
                    shape = MaterialTheme.shapes.large
                ),
            )
        }
    }
}

@Composable
fun DayForecastSection(
    days: List<DayWeather>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier,
        content = {
            items(days) { day ->
                DayForecastItem(
                    dayWeather = day,
                    modifier = Modifier.padding(vertical = 40.dp, horizontal = 32.dp)
                )
                GradientDivider()
            }
        }
    )
}

@Composable
fun DayForecastItem(
    dayWeather: DayWeather,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            Text(
                text = dayWeather.day,
                style = MaterialTheme.typography.body1.copy(fontSize = 20.sp),
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.54f),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = dayWeather.weather.statusRes),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.87f),
            )
        }
        DegreeText(
            text = dayWeather.weather.temperature.toString(),
            style = MaterialTheme.typography.body1.copy(fontSize = 32.sp),
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.87f),
            modifier = Modifier.padding(end = 32.dp)
        )
        Icon(
            painter = painterResource(id = dayWeather.weather.iconRes),
            contentDescription = stringResource(id = R.string.accessibility_weather_status_icon),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(40.dp)
        )
    }
}