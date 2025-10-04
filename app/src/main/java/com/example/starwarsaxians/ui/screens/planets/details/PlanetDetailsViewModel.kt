package com.example.starwarsaxians.ui.screens.planets.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsaxians.domain.model.Planet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class PlanetDetailsViewModel @Inject constructor() : ViewModel() {

/*    private val _planet = MutableStateFlow<Planet?>(null)
    val planet: StateFlow<Planet?> = _planet

    fun loadPlanet(id: String) {
        viewModelScope.launch {
            // Dummy data for now
            _planet.value = Planet(
                id = id,
                name = "Tatooine",
                population = "200,000",
                climate = TODO(),
                filmIds = TODO()
            )
        }
    }*/
}