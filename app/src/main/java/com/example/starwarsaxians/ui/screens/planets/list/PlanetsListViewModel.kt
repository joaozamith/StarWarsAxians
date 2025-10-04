package com.example.starwarsaxians.ui.screens.planets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwarsaxians.data.repo.StarWarsRepository
import com.example.starwarsaxians.domain.model.Planet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class PlanetsListViewModel @Inject constructor(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val searchQuery = MutableStateFlow<String?>(null)

    val planets: Flow<PagingData<Planet>> =
        searchQuery
            .debounce(300)
            .flatMapLatest { query ->
                repository.getPlanetsPaged(query)
            }
            .cachedIn(viewModelScope)

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query.ifBlank { null }
    }
}