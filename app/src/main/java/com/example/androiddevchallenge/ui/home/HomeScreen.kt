package com.example.androiddevchallenge.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Weather
import com.example.androiddevchallenge.data.WeatherStatus
import com.example.androiddevchallenge.data.mockSunnyWeather
import com.example.androiddevchallenge.ui.common.DegreeText
import com.example.androiddevchallenge.ui.theme.LocalImages
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onFutureForeCastClicked: () -> Unit,
    onHamburgerClicked: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetGesturesEnabled = false,
        sheetContent = {
            DayChanges(
                showFutureForecastIcon = bottomSheetScaffoldState.bottomSheetState.isCollapsed,
                onFutureForeCastClicked = onFutureForeCastClicked,
                onCollapseIconClicked = {
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                    viewModel.onTodayChangesExpansionStateChanged()
                },
                onTodayChangesClicked = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                    viewModel.onTodayChangesExpansionStateChanged()
                },
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            )
        },
        sheetPeekHeight = BottomSheetPeekHeight
    ) {
        HomeContent(onHamburgerClicked = onHamburgerClicked)
    }
}

@Composable
fun HomeContent(onHamburgerClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxSize()
    ) {
        HomeTopAppBar(onHamburgerClicked = onHamburgerClicked)
        HomeWeatherContent(
            weather = mockSunnyWeather,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 32.dp)
        )
        Text(
            text = mockSunnyWeather.city,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(start = 32.dp, end = 16.dp, bottom = BottomSheetPeekHeight + 16.dp)
        )
    }
}

@Composable
fun HomeWeatherContent(weather: Weather, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        WeatherInfo(
            weather = weather,
            modifier = Modifier.align(Alignment.TopStart)
        )
        WeatherAnimationEffect(weather)
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
fun WeatherAnimationEffect(weather: Weather) {
    when (weather.status) {
        WeatherStatus.Rainy -> {
            BoxWithConstraints {
                RainEffect(
                    width = maxWidth,
                    height = maxHeight,
                    drop = {
                        RainDrop(
                            modifier = Modifier
                                .width(2.dp)
                                .height(4.dp)
                                .background(MaterialTheme.colors.onPrimary)
                        )
                    }
                )
            }
        }
        WeatherStatus.Snowy -> {
            BoxWithConstraints {
                RainEffect(
                    width = maxWidth,
                    height = maxHeight,
                    randomizeDropSize = true,
                    drop = {
                        SnowDrop(
                            modifier = Modifier,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                )
            }
        }
        WeatherStatus.Sunny -> {
        }
        WeatherStatus.Cloudy -> TODO()
        WeatherStatus.PartialCloudy -> TODO()
        WeatherStatus.ThunderStorm -> TODO()
        WeatherStatus.Tornado -> TODO()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherInfo(
    weather: Weather,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {

    val isTodayChangesExpanded by viewModel.todayChangeExpanded.observeAsState(false)

    val temperatureFontSize by animateIntAsState(
        targetValue = if (isTodayChangesExpanded) 28 else 60
    )

    val temperatureStartSpacing by animateDpAsState(
        targetValue = if (isTodayChangesExpanded) 72.dp else 0.dp
    )

    val weatherIconTopOffset by animateDpAsState(
        targetValue = if (isTodayChangesExpanded) 52.dp else 0.dp
    )

    val weatherInfoTopOffset by animateDpAsState(
        targetValue = if (isTodayChangesExpanded) 0.dp else 24.dp
    )

    Column(
        modifier = modifier.offset(y = -weatherInfoTopOffset)
    ) {
        DegreeText(
            text = weather.temperature.toString(),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h2.copy(fontSize = temperatureFontSize.sp),
            modifier = Modifier.padding(start = temperatureStartSpacing)
        )
        AnimatedVisibility(visible = !isTodayChangesExpanded) {
            Text(
                text = stringResource(id = weather.statusRes),
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Icon(
            painter = painterResource(id = weather.iconRes),
            contentDescription = stringResource(R.string.accessibility_weather_status_icon),
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .offset(y = -weatherIconTopOffset)
                .size(56.dp)
                .padding(top = 8.dp)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeTopAppBar(onHamburgerClicked: () -> Unit, viewModel: HomeViewModel = viewModel()) {
    val isTodayChangesExpanded by viewModel.todayChangeExpanded.observeAsState(false)

    val appbarBottomPadding by animateDpAsState(
        targetValue = if (isTodayChangesExpanded) 16.dp else 32.dp
    )

    val appbarBottomCurve by animateDpAsState(
        targetValue = if (isTodayChangesExpanded) 64.dp else 48.dp
    )

    val appbarTitleSize by animateIntAsState(
        targetValue = if (isTodayChangesExpanded) 16 else 20
    )

    val appbarLogoSize by animateDpAsState(
        targetValue = if (isTodayChangesExpanded) 32.dp else 56.dp
    )

    val hamburgerBottomCurve by animateDpAsState(
        targetValue = if (isTodayChangesExpanded) 48.dp else 0.dp
    )

    val hamburgerVerticalPadding by animateDpAsState(
        targetValue = if (isTodayChangesExpanded) 32.dp else 16.dp
    )

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(bottomStart = appbarBottomCurve)
                )
                .padding(start = 32.dp, end = 16.dp, top = 32.dp, bottom = appbarBottomPadding)
        ) {
            Image(
                painter = painterResource(id = LocalImages.current.logo),
                contentDescription = stringResource(
                    R.string.accessibility_logo
                ),
                modifier = Modifier
                    .width(appbarLogoSize)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h6.copy(fontSize = appbarTitleSize.sp)
            )
        }

        Column(
            modifier = Modifier.width(72.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.secondary,
                        shape = RoundedCornerShape(bottomStart = hamburgerBottomCurve)
                    )
                    .padding(horizontal = 16.dp, vertical = hamburgerVerticalPadding),
                onClick = onHamburgerClicked
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_hamburger),
                    contentDescription = stringResource(
                        R.string.accessibility_hamburger
                    ),
                    tint = MaterialTheme.colors.onSecondary,
                )
            }

            AnimatedVisibility(visible = !isTodayChangesExpanded) {
                AppBarDate(modifier = Modifier.fillMaxWidth())
            }
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

private val BottomSheetPeekHeight = 80.dp