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
val StarWarsYellow = Color(0xFFFFE81F)

// Tipografia personalizada
val StarWarsTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = StarWarsFont,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        color = Color(0xFFFFE81F) // Amarelo oficial
    ),
    bodyLarge = TextStyle(
        fontFamily = StarWarsFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color(0xFFFFE81F)
    )
)

val StarWarsColors = darkColorScheme(
    primary = Color(0xFFFFE81F),
    onPrimary = Color.Black,
    background = Color.Black,
    surface = Color.Black,
    onBackground = Color(0xFFFFE81F),
    onSurface = Color(0xFFFFE81F)
)


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun StarWarsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = StarWarsColors,
        typography = StarWarsTypography,
        content = content
    )
}