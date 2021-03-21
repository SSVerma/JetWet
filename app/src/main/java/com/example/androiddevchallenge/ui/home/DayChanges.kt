package com.example.androiddevchallenge.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.HourlyWeather
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.ui.common.AppIcons
import com.example.androiddevchallenge.ui.common.DegreeText
import com.example.androiddevchallenge.ui.common.DottedDivider
import com.example.androiddevchallenge.ui.theme.yellow200

@Composable
fun DayChanges(
    onFutureForeCastClicked: () -> Unit,
    onTodayChangesClicked: () -> Unit,
    onCollapseIconClicked: () -> Unit,
    showFutureForecastIcon: Boolean,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    Column(modifier = modifier) {
        DayChangesHeader(
            showFutureForecastIcon = showFutureForecastIcon,
            onFutureForeCastClicked = onFutureForeCastClicked,
            onCollapseIconClicked = onCollapseIconClicked,
            modifier = Modifier
                .clickable {
                    onTodayChangesClicked()
                }
                .padding(horizontal = 8.dp)
        )
        DayHighlights(weather = viewModel.currentWeather, modifier = Modifier.padding(top = 16.dp))
        HourlyWeatherReport(
            hourlyGroupedWeather = viewModel.hourlyGroupedWeather,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DayChangesHeader(
    showFutureForecastIcon: Boolean,
    onFutureForeCastClicked: () -> Unit,
    onCollapseIconClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = AppIcons.Timer,
            contentDescription = stringResource(R.string.accessibility_today_changes_icon),
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = stringResource(R.string.today_changes),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 16.sp),
            modifier = Modifier.weight(1f)
        )
        AnimatedVisibility(visible = showFutureForecastIcon) {
            IconButton(onClick = onFutureForeCastClicked) {
                Icon(
                    imageVector = AppIcons.CalendarToday,
                    contentDescription = stringResource(R.string.accessibility_future_forecast_icon),
                    tint = MaterialTheme.colors.secondary
                )
            }
        }
        AnimatedVisibility(visible = !showFutureForecastIcon) {
            IconButton(onClick = onCollapseIconClicked) {
                Icon(
                    imageVector = AppIcons.ExpandMore,
                    contentDescription = stringResource(R.string.accessibility_future_forecast_icon),
                    tint = MaterialTheme.colors.secondary
                )
            }
        }
    }
}

@Composable
fun DayHighlights(weather: Weather, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        DayHighlightItem(
            iconRes = weather.iconRes,
            text = stringResource(id = weather.statusRes),
            iconColor = MaterialTheme.colors.onSurface,
            modifier = Modifier.weight(0.5f)
        )
        DayHighlightItem(
            iconRes = R.drawable.ic_wind,
            text = weather.wind,
            iconColor = MaterialTheme.colors.onSurface,
            modifier = Modifier.weight(0.5f)
        )
    }
}

@Composable
fun DayHighlightItem(
    @DrawableRes iconRes: Int,
    text: String,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier
                .padding(end = 12.dp)
                .size(40.dp)
                .background(
                    color = iconColor.copy(alpha = 0.12f),
                    shape = CircleShape
                )
                .padding(8.dp),
        )
        Text(text = text, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun HourlyWeatherReport(
    hourlyGroupedWeather: List<List<HourlyWeather>>,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        hourlyGroupedWeather.forEach { groups ->
            GroupedHourlyWeather(
                groups = groups,
                dominatingHourWeather = remember { groups.random() },
            )
        }
    }
}

@Composable
fun GroupedHourlyWeather(
    groups: List<HourlyWeather>,
    dominatingHourWeather: HourlyWeather,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Column(modifier = Modifier.weight(0.25f)) {
            groups.forEach {
                HourItem(
                    hour = it.hour,
                    isCurrentHour = it.isCurrentHour,
                    modifier = Modifier.padding(end = 16.dp, top = 16.dp, bottom = 16.dp)
                )
                DottedDivider()
            }
        }
        DominatingWeatherCard(
            weatherIllusRes = dominatingHourWeather.weather.illustrationRes,
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier
                .weight(0.50f)
                .height(190.dp)
        )
        Column(modifier = Modifier.weight(0.25f)) {
            groups.forEach {
                HourlyWeatherItem(
                    weatherIconRes = it.weather.iconRes,
                    temperature = it.weather.temperature,
                    isCurrentHour = it.isCurrentHour,
                    modifier = Modifier.padding(start = 16.dp, top = 13.dp, bottom = 13.dp)
                )
                DottedDivider()
            }
        }
    }
}

@Composable
fun HourItem(hour: String, isCurrentHour: Boolean, modifier: Modifier = Modifier) {
    val contentColor = if (isCurrentHour) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.onSurface//.copy(alpha = 0.54f)
    }

    Text(
        text = hour,
        color = contentColor,
        style = MaterialTheme.typography.body2,
        modifier = modifier
    )
}

@Composable
fun HourlyWeatherItem(
    @DrawableRes weatherIconRes: Int,
    temperature: Int,
    isCurrentHour: Boolean,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isCurrentHour) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.onSurface//.copy(alpha = 0.54f)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = weatherIconRes),
            contentDescription = stringResource(id = R.string.accessibility_weather_status_icon),
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        DegreeText(
            text = temperature.toString(),
            style = MaterialTheme.typography.body2,
            color = contentColor
        )
    }
}

@Composable
fun DominatingWeatherCard(
    @DrawableRes weatherIllusRes: Int,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomEnd = 24.dp)
            )
    ) {
        Image(
            painter = painterResource(id = weatherIllusRes),
            contentDescription = stringResource(
                id = R.string.accessibility_weather_illustration
            ),
            contentScale = ContentScale.FillHeight
        )
    }
}

