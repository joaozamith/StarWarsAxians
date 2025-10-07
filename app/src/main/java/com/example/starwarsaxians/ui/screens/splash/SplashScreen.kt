package com.example.starwarsaxians.ui.screens.splash

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.starwarsaxians.R
import com.example.starwarsaxians.ui.components.StarWarsBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    StarWarsBackground {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            var alpha by remember { mutableFloatStateOf(0f) }

            LaunchedEffect(Unit) {
                animate(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = tween(2000)
                ) { value, _ -> alpha = value }
                delay(1500)
                onFinished()
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.starwars_logo),
                    contentDescription = "Star Wars Logo",
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .alpha(alpha)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .alpha(alpha),
                    contentAlignment = Alignment.Center
                ) {
                    LightsaberBlade(
                        color = Color(0xFF3AA1FF),
                        glowColor = Color(0xFF05A7CC),
                        rotation = -45f
                    )
                    LightsaberHandle(
                        rotation = -45f,
                        alignment = Alignment.BottomStart
                    )

                    LightsaberBlade(
                        color = Color(0xFFFF0000),
                        glowColor = Color(0xFFFF0000),
                        rotation = 45f
                    )
                    LightsaberHandle(
                        rotation = 45f,
                        alignment = Alignment.BottomEnd
                    )
                }
            }
        }
    }
}

@Composable
fun LightsaberBlade(
    color: Color,
    glowColor: Color,
    rotation: Float
) {
    Box(
        modifier = Modifier
            .width(6.dp)
            .height(200.dp)
            .graphicsLayer {
                rotationZ = rotation
                shadowElevation = 10f
            }
            .clip(RoundedCornerShape(13.dp))
            .background(color)
            .shadow(
                elevation = 14.dp,
                shape = RoundedCornerShape(13.dp),
                ambientColor = glowColor.copy(alpha = 0.8f),
                spotColor = glowColor.copy(alpha = 0.8f)
            )
    )
}

@Composable
fun BoxScope.LightsaberHandle(
    rotation: Float,
    alignment: Alignment
) {
    Image(
        painter = painterResource(id = R.drawable.lightsaber),
        contentDescription = "Lightsaber Handle",
        modifier = Modifier
            .size(width = 30.dp, height = 40.dp)
            .graphicsLayer { rotationZ = rotation }
            .align(alignment)
    )
}

