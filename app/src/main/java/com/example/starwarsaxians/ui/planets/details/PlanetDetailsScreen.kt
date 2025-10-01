package com.example.starwarsaxians.ui.planets.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun PlanetDetailsScreen(
    planetId: String,
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Planet $planetId") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text("Planet Details Placeholder")
            Button(onClick = { navigator.popBackStack() }) {
                Text("Back")
            }
        }
    }
}