package com.example.starwarsaxians.ui.planets.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailsScreen(
    planetId: String,
    viewModel: PlanetDetailsViewModel = hiltViewModel()
) {
    val planet by viewModel.planet.collectAsState()

    LaunchedEffect(planetId) {
        viewModel.loadPlanet(planetId)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(planet?.name ?: "Loading...") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            planet?.let {
                Text("Planet: ${it.name}")
                Text("Population: ${it.population}")
            } ?: CircularProgressIndicator()
        }
    }
}