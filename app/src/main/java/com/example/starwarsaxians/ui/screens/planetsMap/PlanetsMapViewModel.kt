package com.example.starwarsaxians.ui.screens.planetsMap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsaxians.data.repo.StarWarsRepository
import com.example.starwarsaxians.domain.model.Character
import com.example.starwarsaxians.domain.model.Planet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlanetsMapViewModel @Inject constructor(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _planets = MutableStateFlow<List<Planet>>(emptyList())
    val planets: StateFlow<List<Planet>> = _planets

    private val _selectedPlanet = MutableStateFlow<Planet?>(null)
    val selectedPlanet: StateFlow<Planet?> = _selectedPlanet

    private val _planetCharacters = MutableStateFlow<List<Character>>(emptyList())
    val planetCharacters: StateFlow<List<Character>> = _planetCharacters

    private val _isLoadingCharacters = MutableStateFlow(false)
    val isLoadingCharacters: StateFlow<Boolean> = _isLoadingCharacters

    init {
        viewModelScope.launch {
            _planets.value = repository.getAllPlanets()
        }
    }

    fun selectPlanet(planet: Planet) {
        _selectedPlanet.value = planet
        viewModelScope.launch {
            _isLoadingCharacters.value = true
            try {
                _planetCharacters.value = repository.getCharactersByPlanet(planet.id)
            } finally {
                _isLoadingCharacters.value = false
            }
        }
    }

    fun clearSelection() {
        _selectedPlanet.value = null
        _planetCharacters.value = emptyList()
    }
}
