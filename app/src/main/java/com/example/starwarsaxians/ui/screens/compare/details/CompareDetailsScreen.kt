package com.example.starwarsaxians.ui.screens.compare.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.starwarsaxians.R
import com.example.starwarsaxians.domain.model.Character
import com.example.starwarsaxians.ui.components.StarWarsBackground
import com.example.starwarsaxians.ui.screens.compare.CompareCharactersViewModel
import com.example.starwarsaxians.ui.theme.StarWarsFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareDetailsScreen(
    char1Id: String,
    char2Id: String,
    onBack: () -> Unit,
    viewModel: CompareCharactersViewModel = hiltViewModel()
) {
    val char1 by produceState<Character?>(initialValue = null) {
        value = viewModel.getCharacterById(char1Id)
    }
    val char2 by produceState<Character?>(initialValue = null) {
        value = viewModel.getCharacterById(char2Id)
    }

    if (char1 == null || char2 == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        return
    }

    val image1 = when (char1!!.name.lowercase()) {
        "luke skywalker" -> R.drawable.lukeskywalker
        "darth vader" -> R.drawable.darthvader
        else -> R.drawable.chewbacca
    }

    val image2 = when (char2!!.name.lowercase()) {
        "luke skywalker" -> R.drawable.lukeskywalker
        "darth vader" -> R.drawable.darthvader
        else -> R.drawable.chewbacca
    }

    StarWarsBackground {
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
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = image1),
                            contentDescription = char1!!.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .border(2.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                        )
                        Image(
                            painter = painterResource(id = image2),
                            contentDescription = char2!!.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .border(2.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                        )
                    }

                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp)
                            .size(36.dp)
                            .background(
                                color = Color.Black.copy(alpha = 0.6f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            item {
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = char1!!.name,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = StarWarsFont,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = char2!!.name,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = StarWarsFont,
                        textAlign = TextAlign.Center
                    )
                }
            }

            item {
                ComparisonSection(
                    title = "biographical information",
                    entries = listOf(
                        Triple("homeworld", char1!!.homeworldId ?: "-", char2!!.homeworldId ?: "-"),
                        Triple("born", char1!!.birthYear ?: "-", char2!!.birthYear ?: "-")
                    )
                )
            }

            item {
                ComparisonSection(
                    title = "physical description",
                    entries = listOf(
                        Triple("gender", char1!!.gender ?: "-", char2!!.gender ?: "-"),
                        Triple("height", "${char1!!.height ?: "-"} m", "${char2!!.height ?: "-"} m"),
                        Triple("mass", "${char1!!.mass ?: "-"} kg", "${char2!!.mass ?: "-"} kg")
                    )
                )
            }
        }
    }
}

@Composable
fun ComparisonSection(
    title: String,
    entries: List<Triple<String, String, String>>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = StarWarsFont,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            textDecoration = TextDecoration.Underline
        )
        Spacer(Modifier.height(8.dp))

        entries.forEach { (label, left, right) ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    label,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = StarWarsFont,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        left,
                        color = Color.White,
                        fontFamily = StarWarsFont,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        right,
                        color = Color.White,
                        fontFamily = StarWarsFont,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}