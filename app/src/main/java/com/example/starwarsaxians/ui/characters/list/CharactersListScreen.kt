package com.example.starwarsaxians.ui.characters.list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.starwarsaxians.ui.destinations.CharacterDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable
fun CharactersListScreen(
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Characters") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Text("Characters list placeholder")

            Button(
                onClick = {
                    navigator.navigate(CharacterDetailsScreenDestination(characterId = "1"))
                }
            ) {
                Text("Go to Character 1")
            }
        }
    }
}