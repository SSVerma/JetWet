package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.data.mockWeather
import com.example.androiddevchallenge.ui.common.AppIcons
import com.example.androiddevchallenge.ui.common.DegreeText
import com.example.androiddevchallenge.ui.theme.LocalImages
import com.example.androiddevchallenge.ui.theme.yellow200

@Composable
fun HomeScreen(onFutureForeCastClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxSize()
    ) {
        HomeTopAppBar()
        HomeWeatherContent(
            weather = mockWeather,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 32.dp)
        )
        Text(
            text = mockWeather.city,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(start = 32.dp, end = 16.dp, bottom = 16.dp)
        )
        HomeTodayChangesBar(
            onFutureForeCastClicked,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .padding(horizontal = 32.dp, vertical = 16.dp)
        )
    }
}

@Composable
fun HomeWeatherContent(weather: Weather, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        WeatherInfo(
            weather = weather,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = (-32).dp)
        )
        Image(
            painter = painterResource(id = weather.illustrationRes),
            contentDescription = stringResource(R.string.accessibility_weather_illustration),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight()
                .scale(scaleX = -1f, scaleY = 1f)
                .offset(x = (-80).dp)
        )
    }
}

@Composable
fun WeatherInfo(weather: Weather, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        DegreeText(
            text = weather.temperature.toString(),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h2
        )
        Text(
            text = stringResource(id = weather.statusRes),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 8.dp)
        )
        Icon(
            painter = painterResource(id = weather.iconRes),
            contentDescription = stringResource(R.string.accessibility_weather_status_icon),
            tint = MaterialTheme.colors.onPrimary,
        )
    }
}

@Composable
fun HomeTopAppBar() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(bottomStart = 48.dp)
                )
                .padding(start = 32.dp, end = 16.dp, top = 32.dp, bottom = 32.dp)
        ) {
            Image(
                painter = painterResource(id = LocalImages.current.logo),
                contentDescription = stringResource(
                    R.string.accessibility_logo
                ),
                modifier = Modifier
                    .width(56.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h6
            )
        }

        Column(
            modifier = Modifier
                .width(72.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(bottomStart = 0.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_hamburger),
                    contentDescription = stringResource(
                        R.string.accessibility_hamburger
                    ),
                    tint = MaterialTheme.colors.onSecondary,
                )
            }

            AppBarDate(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun AppBarDate(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(
                color = MaterialTheme.colors.secondaryVariant,
                shape = RoundedCornerShape(bottomStart = 48.dp)
            )
            .padding(vertical = 24.dp)
    ) {
        Text(
            text = "Sat",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary
        )
        Text(
            text = "16",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun HomeTodayChangesBar(onFutureForeCastClicked: () -> Unit, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Icon(
            imageVector = AppIcons.Timer,
            contentDescription = stringResource(R.string.accessibility_today_changes_icon),
            tint = yellow200, /*Same color irrespective of theme*/
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = stringResource(R.string.today_changes),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 16.sp),
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onFutureForeCastClicked) {
            Icon(
                imageVector = AppIcons.CalendarToday,
                contentDescription = stringResource(R.string.accessibility_future_forecast_icon),
                tint = MaterialTheme.colors.secondary
            )
        }
    }
}