package com.example.starwarsaxians.ui.screens.characters.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    characterId: String,
    onPlanetClick: (String) -> Unit,
    viewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    val character by viewModel.character.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.loadCharacter(characterId)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(character?.name ?: "Loading...") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            character?.let {
                Text("Name: ${it.name}")
                Text("Homeworld: ${it.homeworldName}")
                Button(onClick = { onPlanetClick(it.homeworldId) }) {
                    Text("Go to Homeworld")
                }
            } ?: CircularProgressIndicator()
        }
    }
}