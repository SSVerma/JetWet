package com.example.androiddevchallenge.ui.common

import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle

@Composable
fun DegreeText(
    text: String,
    color: Color = LocalContentColor.current,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = buildAnnotatedString {
            append(text = text)
            withStyle(SpanStyle(baselineShift = BaselineShift(0.0f))) {
                append("\u00B0") //Degree Symbol
            }
        },
        color = color,
        style = style
    )
}