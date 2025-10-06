package com.example.starwarsaxians.ui.screens.compare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsaxians.data.repo.StarWarsRepository
import com.example.starwarsaxians.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompareCharactersViewModel @Inject constructor(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _selectedCharacter1 = MutableStateFlow<Character?>(null)
    val selectedCharacter1: StateFlow<Character?> = _selectedCharacter1

    private val _selectedCharacter2 = MutableStateFlow<Character?>(null)
    val selectedCharacter2: StateFlow<Character?> = _selectedCharacter2

    fun onCharacterSelected(slot: Int, id: String) {
        viewModelScope.launch {
            val character = repository.getCharacterById(id)
            if (slot == 1) _selectedCharacter1.value = character
            else _selectedCharacter2.value = character
        }
    }

    suspend fun getCharacterById(id: String): Character? {
        return try {
            repository.getCharacterById(id)
        } catch (e: Exception) {
            null
        }
    }
}