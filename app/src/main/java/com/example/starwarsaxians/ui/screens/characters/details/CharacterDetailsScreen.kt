package com.example.starwarsaxians.ui.screens.characters.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.starwarsaxians.R
import com.example.starwarsaxians.ui.components.InfoSection
import com.example.starwarsaxians.ui.components.StarWarsBackground
import com.example.starwarsaxians.ui.theme.StarWarsFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    characterId: String,
    onBack: () -> Unit,
    viewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    val character by viewModel.character.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.loadCharacter(characterId)
    }

    StarWarsBackground {
        character?.let { char ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.lukeskywalker),
                            contentDescription = char.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        IconButton(
                            onClick = onBack,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(16.dp)
                                .size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        IconButton(
                            onClick = { viewModel.toggleFavorite() },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp)
                                .size(40.dp)
                                .background(
                                    color = Color.Black.copy(alpha = 0.6f),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = if (char.isFavorite) Icons.Default.Star else Icons.Outlined.StarBorder,
                                contentDescription = "Favorite",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                item {
                    Text(
                        text = char.name.uppercase(),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontFamily = StarWarsFont,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }

                item {
                    InfoSection(
                        title = "BIOGRAPHICAL INFORMATION",
                        items = listOf(
                            "HOMEWORLD" to char.homeworldId.toString(),
                            "BORN" to (char.birthYear ?: "-"),
                            "DIED" to ("34BBY")
                        )
                    )
                }

                item {
                    InfoSection(
                        title = "PHYSICAL DESCRIPTION",
                        items = listOf(
                            "SPECIES" to char.speciesIds.joinToString().ifEmpty { "Human" },
                            "GENDER" to (char.gender ?: "-"),
                            "PRONOUNS" to (char.gender ?: "-"),
                            "HEIGHT" to (char.height ?: "-"),
                            "MASS" to (char.mass ?: "-"),
                        )
                    )
                }

                item {
                    Text(
                        text = "STORY",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontFamily = StarWarsFont,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                    )

                    Text(
                        text = "${char.name}, a Force-sensitive human male, was a legendary Jedi Master who fought in the Galactic Civil War during the reign of the Galactic Empire. Along with his companions, Princess Leia Organa and General Han Solo, ${char.name} served as a revolutionary on the side of the Alliance to Restore the Republic—an organization committed to the downfall of the Galactic Empire and the restoration of democracy. Following the war, ${char.name} became a living legend, and was remembered as one of the greatest Jedi in galactic history.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = StarWarsFont,
                            color = MaterialTheme.colorScheme.primary,
                            lineHeight = 20.sp
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}
