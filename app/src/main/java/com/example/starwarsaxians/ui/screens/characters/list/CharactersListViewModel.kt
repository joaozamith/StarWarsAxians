package com.example.starwarsaxians.ui.screens.characters.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwarsaxians.data.repo.StarWarsRepository
import com.example.starwarsaxians.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val searchQuery = MutableStateFlow<String?>(null)
    private val _sortAscending = MutableStateFlow<Boolean?>(null)
    val sortAscending: StateFlow<Boolean?> = _sortAscending

    val characters: Flow<PagingData<Character>> =
        combine(searchQuery.debounce(300), _sortAscending) { query, sortAsc ->
            query to sortAsc
        }.flatMapLatest { (query, sortAsc) ->
            if (sortAsc == null) {
                repository.getCharactersPaged(query)
            } else {
                repository.getCharactersPagedSorted(query, sortAsc)
            }
        }.cachedIn(viewModelScope)

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query.ifBlank { null }
    }

    fun setSortOrder(ascending: Boolean?) {
        _sortAscending.value = ascending
    }
}