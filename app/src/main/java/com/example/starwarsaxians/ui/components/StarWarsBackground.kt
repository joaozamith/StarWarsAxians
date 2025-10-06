package com.example.starwarsaxians.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.starwarsaxians.R
import com.example.starwarsaxians.ui.theme.AppThemeViewModel

@Composable
fun StarWarsBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val appThemeViewModel: AppThemeViewModel = hiltViewModel()
    val isDarthVaderMode by appThemeViewModel.isDarthVaderMode.collectAsState()

    val backgroundRes = if (isDarthVaderMode) {
        R.drawable.darth_vader_background
    } else {
        R.drawable.background_stars
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        if (isDarthVaderMode) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0x80000000))
            )
        }

        content()
    }
}
