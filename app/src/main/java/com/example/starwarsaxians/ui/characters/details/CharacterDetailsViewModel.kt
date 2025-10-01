package com.example.starwarsaxians.ui.characters.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharacterDetails(
    val id: String,
    val name: String,
    val homeworldId: String,
    val homeworldName: String
)

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor() : ViewModel() {

    private val _character = MutableStateFlow<CharacterDetails?>(null)
    val character: StateFlow<CharacterDetails?> = _character

    fun loadCharacter(id: String) {
        viewModelScope.launch {
            // Dummy data for now
            _character.value = CharacterDetails(
                id = id,
                name = when (id) {
                    "1" -> "Luke Skywalker"
                    "2" -> "Leia Organa"
                    "3" -> "Han Solo"
                    else -> "Unknown"
                },
                homeworldId = "100",
                homeworldName = "Tatooine"
            )
        }
    }
}