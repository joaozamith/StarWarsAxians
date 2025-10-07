package com.example.starwarsaxians.ui.screens.compare

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.starwarsaxians.domain.model.Character
import com.example.starwarsaxians.ui.components.CharacterSelectedCard
import com.example.starwarsaxians.ui.components.DefaultCard
import com.example.starwarsaxians.ui.components.StarWarsBackground
import com.example.starwarsaxians.ui.theme.StarWarsFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareCharactersScreen(
    navController: NavController,
    onNavigateToCharacters: (Int) -> Unit,
    onCompare: (Character, Character) -> Unit,
    onBack: () -> Unit,
    viewModel: CompareCharactersViewModel = hiltViewModel()
) {
    val char1 by viewModel.selectedCharacter1.collectAsState()
    val char2 by viewModel.selectedCharacter2.collectAsState()

    LaunchedEffect(Unit) {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getStateFlow<String?>("selectedCharacterId", null)
            ?.collect { id ->
                if (id != null) {
                    val slot = navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.getStateFlow("selectedSlot", 1)
                        ?.value ?: 1
                    viewModel.onCharacterSelected(slot, id)
                    navController.currentBackStackEntry?.savedStateHandle?.set("selectedCharacterId", null)
                }
            }
    }

    StarWarsBackground {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Compare Characters",
                            color = MaterialTheme.colorScheme.primary,
                            fontFamily = StarWarsFont
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            },
            containerColor = Color.Transparent
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (char1 == null) {
                    DefaultCard(onClick = { onNavigateToCharacters(1) })
                } else {
                    CharacterSelectedCard(character = char1!!, onClick = { onNavigateToCharacters(1) })
                }

                Text(
                    "VS",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium.copy(fontFamily = StarWarsFont)
                )

                if (char2 == null) {
                    DefaultCard(onClick = { onNavigateToCharacters(2) })
                } else {
                    CharacterSelectedCard(character = char2!!, onClick = { onNavigateToCharacters(2) })
                }

                if (char1 != null && char2 != null) {
                    Button(
                        onClick = { onCompare(char1!!, char2!!) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(0.8f)
                    ) {
                        Text(
                            "compare",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = StarWarsFont,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    }
}