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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.R

private val DarkColorPalette = darkColors(
    primary = green600,
    primaryVariant = green900,
    secondary = purple200,
    background = gray,
    surface = gray,
    onPrimary = Color.White,
    onSecondary = gray,
    onBackground = Color.White,
    onSurface = white850
)

private val LightColorPalette = lightColors(
    primary = purple700,
    primaryVariant = purple800,
    secondary = pink500,
    secondaryVariant = pink700,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

private val LightImages = Images(
    logo = R.drawable.ic_logo_light
)

private val DarkImages = Images(
    logo = R.drawable.ic_logo_dark
)

@Composable
fun JetWetTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val images = if (darkTheme) DarkImages else LightImages

    CompositionLocalProvider(
        LocalImages provides images
    ) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
