package com.example.starwarsaxians.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.starwarsaxians.R


val StarWarsFont = FontFamily(
    Font(R.font.starjedi_special, FontWeight.Normal)
)
val JediYellow = Color(0xFFFFE81F)
val SithRed = Color(0xFFB71C1C)

val StarWarsTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = StarWarsFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        color = Color(0xFFFFE81F)
    ),
    bodyLarge = TextStyle(
        fontFamily = StarWarsFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color(0xFFFFE81F)
    )
)

@Composable
fun StarWarsTheme(
    isDarthVaderMode: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (isDarthVaderMode) {
        darkColorScheme(
            primary = SithRed,
            background = Color.Black,
            surface = Color.Black,
            onPrimary = Color.White,
            onSurface = SithRed
        )
    } else {
        darkColorScheme(
            primary = JediYellow,
            background = Color.Black,
            surface = Color.Black,
            onPrimary = Color.Black,
            onSurface = JediYellow
        )
    }

    MaterialTheme(
        colorScheme = colors,
        typography = StarWarsTypography,
        content = content
    )
}