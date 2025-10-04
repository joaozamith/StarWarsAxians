package com.example.starwarsaxians.ui.screens.splash

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import com.example.starwarsaxians.R
import com.example.starwarsaxians.ui.components.StarWarsBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    StarWarsBackground {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            var alpha by remember { mutableFloatStateOf(0f) }

            LaunchedEffect(Unit) {
                animate(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = tween(2000)
                ) { value, _ ->
                    alpha = value
                }
                delay(1500)
                onFinished()
            }

            Image(
                painter = painterResource(R.drawable.starwars_logo),
                contentDescription = "Star Wars Logo",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .alpha(alpha)
            )
        }
    }
}