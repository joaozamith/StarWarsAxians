package com.example.starwarsaxians.ui.characters.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.starwarsaxians.data.repo.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    repository: StarWarsRepository
) : ViewModel() {

    val characters = repository
        .getCharactersPaged()
        .cachedIn(viewModelScope)
}