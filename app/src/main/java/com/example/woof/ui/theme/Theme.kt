package com.example.woof.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = Cyan900,
    surface = Cyan700,
    onSurface = White,
    primary = Grey900,
    onPrimary = White,
    secondary = Grey100
)

private val LightColorScheme = lightColorScheme(
    background = Green100,
    surface = Green50,
    onSurface = Grey900,
    primary = Grey50,
    onPrimary = Grey900,
    secondary = Grey700
)

@Composable
fun WoofTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}