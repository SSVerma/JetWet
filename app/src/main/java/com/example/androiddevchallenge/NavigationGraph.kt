/*
 * Copyright 2020 The Android Open Source Project
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
package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.CityForecastScreen
import com.example.androiddevchallenge.ui.FutureForecastScreen
import com.example.androiddevchallenge.ui.home.HomeScreen

object AppDestinations {
    const val HOME_ROUTE = "home"
    const val FUTURE_FORECAST_ROUTE = "future_forecast"
    const val CITY_FORECAST_ROUTE = "city_forecast"
}

@Composable
fun NavigationGraph(startDestination: String = AppDestinations.HOME_ROUTE) {
    val navController = rememberNavController()

    val actions = remember(navController) { AppNavigationActions(navController = navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppDestinations.HOME_ROUTE) {
            HomeScreen(
                onFutureForeCastClicked = {
                    actions.onFutureForecastClick()
                },
                onHamburgerClicked = {
                    actions.onCityForecastClick()
                }
            )
        }

        composable(AppDestinations.FUTURE_FORECAST_ROUTE) {
            FutureForecastScreen()
        }

        composable(AppDestinations.CITY_FORECAST_ROUTE) {
            CityForecastScreen(
                onBackPressed = {
                    actions.onBackPress()
                }
            )
        }
    }
}

class AppNavigationActions(navController: NavHostController) {
    val onFutureForecastClick: () -> Unit = {
        navController.navigate(AppDestinations.FUTURE_FORECAST_ROUTE)
    }
    val onCityForecastClick: () -> Unit = {
        navController.navigate(AppDestinations.CITY_FORECAST_ROUTE)
    }
    val onBackPress: () -> Unit = {
        navController.navigateUp()
    }
}
