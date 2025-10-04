package com.example.starwarsaxians.ui.screens.species.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwarsaxians.ui.components.StarWarsBackground
import com.example.starwarsaxians.ui.components.StarWarsSearchBar
import com.example.starwarsaxians.ui.theme.StarWarsFont
import com.example.starwarsaxians.ui.theme.StarWarsYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeciesListScreen(
    onSpeciesClick: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: SpeciesListViewModel = hiltViewModel()
) {
    val species = viewModel.species.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }

    StarWarsBackground {
        Scaffold(
            topBar = {
                Column {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Species",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontFamily = StarWarsFont,
                                    color = StarWarsYellow
                                )
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onBack) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = StarWarsYellow
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent
                        )
                    )
                    StarWarsSearchBar(
                        query = searchQuery,
                        onQueryChange = { newQuery ->
                            searchQuery = newQuery
                            viewModel.onSearchQueryChanged(newQuery)
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        placeholder = "Search species..."
                    )
                }
            },
            containerColor = Color.Transparent
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(species.itemCount) { index ->
                        val specie = species[index]
                        if (specie != null) {
                            Text(
                                text = specie.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onSpeciesClick(specie.id) }
                                    .padding(16.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    // Estado inicial (refresh)
                    when (val state = species.loadState.refresh) {
                        is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        is LoadState.Error -> {
                            item {
                                Text(
                                    text = "Error: ${state.error.localizedMessage}",
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }

                        else -> Unit
                    }

                    // Paginação (append)
                    when (val state = species.loadState.append) {
                        is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        is LoadState.Error -> {
                            item {
                                Text(
                                    text = "Error loading more: ${state.error.localizedMessage}",
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}