package com.example.starwarsaxians.ui.characters.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.starwarsaxians.ui.destinations.PlanetDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun CharacterDetailsScreen(
    characterId: String,
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Character $characterId") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text("Character Details Placeholder")

            Button(
                onClick = {
                    navigator.navigate(PlanetDetailsScreenDestination(planetId = "10"))
                }
            ) {
                Text("Go to Planet 10")
            }
        }
    }
}