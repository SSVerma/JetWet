package com.example.androiddevchallenge.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.LocalImages

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

@Composable
fun AppTopAppBar(
    @StringRes titleRes: Int,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(bottomStart = 32.dp)
            )
            .padding(start = 32.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Image(
            painter = painterResource(id = LocalImages.current.logo),
            contentDescription = stringResource(
                R.string.accessibility_logo
            ),
            modifier = Modifier
                .width(32.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = stringResource(id = titleRes),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onBackPressed, modifier = Modifier.align(Alignment.CenterVertically)) {
            Icon(
                imageVector = AppIcons.Close,
                contentDescription = stringResource(id = R.string.accessibility_close_icon)
            )
        }
    }
}

@Composable
fun FilledCircle(
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(
            color = color,
            shape = CircleShape
        )
    )
}