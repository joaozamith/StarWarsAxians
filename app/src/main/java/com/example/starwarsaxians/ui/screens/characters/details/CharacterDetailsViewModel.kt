package com.example.starwarsaxians.ui.screens.characters.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsaxians.data.repo.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _character = MutableStateFlow<com.example.starwarsaxians.domain.model.Character?>(null)
    val character: StateFlow<com.example.starwarsaxians.domain.model.Character?> = _character

    fun loadCharacter(id: String) {
        viewModelScope.launch {
            runCatching {
                repository.getCharacterById(id)
            }.onSuccess { character ->
                _character.value = character
            }.onFailure { error ->
                Log.e("CharacterDetailsViewModel", "Error: ${error.message}")
            }
        }
    }

    fun toggleFavorite() {
        val char = _character.value ?: return
        viewModelScope.launch {
            repository.toggleFavorite(char.id)
            _character.value = char.copy(isFavorite = !char.isFavorite)
        }
    }
}