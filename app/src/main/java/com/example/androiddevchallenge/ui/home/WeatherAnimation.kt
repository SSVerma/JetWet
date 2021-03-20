package com.example.androiddevchallenge.ui.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.utils.generateRandomList

@Composable
fun RainEffect(
    width: Dp,
    height: Dp,
    modifier: Modifier = Modifier,
    drop: @Composable () -> Unit,
    denseFactor: Int = 16,
    dropDimension: DropDimension = DropDefaults.dimensions(),
    randomizeDropSize: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition()

    val sections = (width.value / denseFactor).toInt()

    val duration = remember {
        generateRandomList(
            size = sections,
            from = 1500,
            to = 2500
        )
    }

    val dropWidths = remember {
        if (randomizeDropSize) {
            generateRandomList(
                size = sections,
                from = dropDimension.dropMinWidth.value.toInt(),
                to = dropDimension.dropMaxWidth.value.toInt()
            )
        } else {
            emptyList()
        }
    }

    val dropHeights = remember {
        if (randomizeDropSize) {
            generateRandomList(
                size = sections,
                from = dropDimension.dropMinHeight.value.toInt(),
                to = dropDimension.dropMaxHeight.value.toInt()
            )
        } else {
            emptyList()
        }
    }

    Row(
        modifier = modifier
            .fillMaxSize()
            .rotate(5f),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        for (i in 0 until sections) {

            val position by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = duration[i], easing = LinearEasing)
                )
            )

            Box(
                modifier = Modifier
                    .offset(y = height - (height * position))
                    .width(if (randomizeDropSize) dropWidths[i].dp else dropDimension.width)
                    .height(if (randomizeDropSize) dropHeights[i].dp else dropDimension.height)
            ) {
                drop()
            }
        }
    }
}

@Composable
fun RainDrop(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier)
}

@Composable
fun SnowDrop(
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_snowflake),
        contentDescription = null,
        tint = tint,
        modifier = modifier
    )
}

object DropDefaults {
    private val dropMinWidth = 4.dp
    private val dropMaxWidth = 16.dp
    private val dropMinHeight = 8.dp
    private val dropMaxHeight = 16.dp

    fun dimensions(): DropDimension = DropDimension(
        dropMinWidth = dropMinWidth,
        dropMinHeight = dropMinHeight,
        dropMaxWidth = dropMaxWidth,
        dropMaxHeight = dropMaxHeight
    )
}

data class DropDimension(
    val dropMinWidth: Dp,
    val dropMaxWidth: Dp,
    val dropMinHeight: Dp,
    val dropMaxHeight: Dp,
    val width: Dp = dropMinWidth,
    val height: Dp = dropMinHeight,
)