package com.example.starwarsaxians.ui.characters.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwarsaxians.ui.components.CharacterSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersListScreen(
    onCharacterClick: (String) -> Unit,
    viewModel: CharactersListViewModel = hiltViewModel()
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Characters") }
                )
                CharacterSearchBar(
                    query = searchQuery,
                    onQueryChange = { newQuery ->
                        searchQuery = newQuery
                        viewModel.onSearchQueryChanged(newQuery)
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = "Search characters..."
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(characters.itemCount) { index ->
                val character = characters[index]
                if (character != null) {
                    Text(
                        text = character.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onCharacterClick(character.id) }
                            .padding(16.dp)
                    )
                }
            }

            // Estado inicial (refresh)
            when (val state = characters.loadState.refresh) {
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
            when (val state = characters.loadState.append) {
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

