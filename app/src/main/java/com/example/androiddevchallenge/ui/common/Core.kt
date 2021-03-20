package com.example.androiddevchallenge.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect

val AppIcons = Icons.Default

@Composable
fun DottedDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.32f)
) {
    Canvas(modifier = modifier.fillMaxWidth(), onDraw = {
        drawLine(
            color = color,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height),
            pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(10f, 20f), phase = 25f)
        )
    })
}